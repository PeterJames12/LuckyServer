package org.luckyether.server.service.impl;

import lombok.val;
import org.luckyether.server.model.*;
import org.luckyether.server.service.*;
import org.luckyether.server.util.Ether;
import org.luckyether.server.util.JackpotCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Igor Hnes on 9/4/17.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private static final String ADDRESS_FOR_NEWBIE = "0xbADA6A89904D26E6a1C950d63e4ba27FE81B4829";
    private static final  String ADDRESS_FOR_EXPERIENCED = "0xD00Ede3745d80F885d0B5bf71C80BD70034949a1";
    private static final String ADDRESS_FOR_PROFESSIONAL = "0x90B4F43b617bE3A5D947389921EE25f1f7c39A07";
    private static final String JACKPOT_ADDRESS = "0xA3eb3cE86BAcA5C621dAaBB648320236D5F3C684";
    private static String destination;
    private static final int LIMIT_USERS = 10;

    @Autowired
    private HistoryService historyService;
    @Autowired
    private HelperService helperService;
    @Autowired
    private LuckyGamesService luckyGamesService;
    @Autowired
    private OutTransactionService outTransactionService;
    @Autowired
    private NewbieService newbieService;
    @Autowired
    private ExperiencedService experiencedService;
    @Autowired
    private ProfessionalService professionalService;
    @Autowired
    private JackpotService jackpotService;

    /**
     * {@inheritDoc}.
     */
    @Override
    public synchronized void sendTransaction(String address, Ether ether) throws InterruptedException, ExecutionException, TransactionTimeoutException, IOException, CipherException {
        final TransactionHelper helper = helperService.getHelper();
        Web3j webThreeJ = Web3j.build(new HttpService());
        BigInteger privateKey = new BigInteger(helper.getPrivateKey());
        ECKeyPair ecKeyPair = ECKeyPair.create(privateKey.toByteArray());
        Credentials credentials = Credentials.create(ecKeyPair);
        BigDecimal value = Convert.toWei(ether.toString(), Convert.Unit.ETHER);
        Transfer.sendFundsAsync(webThreeJ, credentials, address, value, Convert.Unit.WEI).get();

        final OutTransaction outTransaction = new OutTransaction();
        outTransaction.setData(LocalDateTime.now().toString());
        outTransaction.setEther(ether.toString());
        outTransaction.setWinnerAddress(address);
        outTransactionService.save(outTransaction);

        Transfer.sendFundsAsync(webThreeJ, credentials, JACKPOT_ADDRESS, value, Convert.Unit.WEI).get();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void listeningTransaction() {
        Web3j webJ = Web3j.build(new HttpService());
        webJ.transactionObservable()
                .subscribe(t -> {
                    if (null != t.getTo()) {
                        destination = t.getTo();
                    }
                    if (destination.equalsIgnoreCase(ADDRESS_FOR_NEWBIE)
                            && Ether.BETS_NEWBIE.toString()
                            .equals(Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString())) {
                        saveNewbieTransactions(t.getFrom());
                        saveAddress(t.getFrom(),
                                Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString(), t.getHash());
                    } else if (destination.equalsIgnoreCase(ADDRESS_FOR_EXPERIENCED)
                            && Ether.BETS_EXPERIENCED.toString()
                            .equals(Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString())) {
                        saveExperiencedTransactions(t.getFrom());
                        saveAddress(t.getFrom(),
                                Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString(), t.getHash());
                    } else if (destination.equalsIgnoreCase(ADDRESS_FOR_PROFESSIONAL)
                            && Ether.BETS_PROFESSIONAL.toString()
                            .equals(Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString())) {
                        saveProfessionalTransactions(t.getFrom());
                        saveAddress(t.getFrom(),
                                Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString(), t.getHash());
                    }
                }, Throwable::printStackTrace);
    }

    /**
     * @param address is participant's wallet address.
     * @param ether is how much he rated.
     */
    private synchronized void saveAddress(String address, String ether, String hash) {
        final TransactionHistory history = new TransactionHistory();
        history.setSenderAddress(address);
        history.setEther(ether);
        history.setTransactionHash(hash);
        history.setDate(LocalDateTime.now().toString());
        historyService.save(history);
    }

    /**
     * @param address is going to be save in database.
     */
    private void saveNewbieTransactions(String address) {
        val newbie = new Newbie();
        newbie.setAddress(address);
        newbieService.save(newbie);
        final Jackpot jackpot = new Jackpot();
        jackpot.setAddress(address);
        jackpot.setCount(JackpotCount.NEWBIE);
        jackpotService.save(jackpot);
        final Long id = newbieService.countById();
        if (id % LIMIT_USERS == 0) {
            final Thread thread = new Thread(() -> sendNewBie(id - LIMIT_USERS, id));
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * @param address is going to be save in database.
     */
    private void saveExperiencedTransactions(String address) {
        val experienced = new Experienced();
        experienced.setAddress(address);
        experiencedService.save(experienced);
        final Jackpot jackpot = new Jackpot();
        jackpot.setAddress(address);
        jackpot.setCount(JackpotCount.EXPERIENCED);
        jackpotService.save(jackpot);
        final Long id = experiencedService.countById();
        if (id % LIMIT_USERS == 0) {
            final Thread thread = new Thread(() -> sendExperienced(id - LIMIT_USERS, id));
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * @param address is going to be save in database.
     */
    private void saveProfessionalTransactions(String address) {
        val professional = new Professional();
        professional.setAddress(address);
        professionalService.save(professional);
        final Jackpot jackpot = new Jackpot();
        jackpot.setAddress(address);
        jackpot.setCount(JackpotCount.PROFESSIONAL);
        jackpotService.save(jackpot);
        final Long id = professionalService.countById();
        if (id % LIMIT_USERS == 0) {
            final Thread thread = new Thread(() -> sendProfessional(id - LIMIT_USERS, id));
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * Find users from table newbie between given id and make their happy.
     */
    private void sendNewBie(Long from, Long to) {
        final List<Newbie> newbies = newbieService.getBetweenId(from, to);
        List<String> addressList = new LinkedList<>();
        newbies.forEach(s -> addressList.add(s.getAddress()));
        luckyGamesService.newbieTransaction(addressList);
    }

    /**
     * Find users from table experienced between given id and make their happy.
     */
    private void sendExperienced(Long from, Long to) {
        final List<Experienced> experienced = experiencedService.getBetweenId(from, to);
        List<String> addressList = new LinkedList<>();
        experienced.forEach(s -> addressList.add(s.getAddress()));
        luckyGamesService.experiencedTransaction(addressList);
    }

    /**
     * Find users from table professional between given id and make their happy.
     */
    private void sendProfessional(Long from, Long to) {
        final List<Professional> professionals = professionalService.getBetweenId(from, to);
        List<String> addressList = new LinkedList<>();
        professionals.forEach(s -> addressList.add(s.getAddress()));
        luckyGamesService.professionalTransaction(addressList);
    }
}
