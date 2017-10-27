package org.luckyether.server.service.impl;

import lombok.val;
import org.luckyether.server.model.*;
import org.luckyether.server.repository.ExperiencedRepository;
import org.luckyether.server.repository.NewbieRepository;
import org.luckyether.server.repository.ProfessionalRepository;
import org.luckyether.server.repository.TransactionHistoryRepository;
import org.luckyether.server.service.HelperService;
import org.luckyether.server.service.LuckyGamesService;
import org.luckyether.server.service.TransactionService;
import org.luckyether.server.util.Ether;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

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
public class TransactionServiceImpl implements TransactionService {

    private static final  String ADDRESS_FOR_NEWBIE = "0xbADA6A89904D26E6a1C950d63e4ba27FE81B4829";
    private static final  String ADDRESS_FOR_EXPERIENCED = "0xD00Ede3745d80F885d0B5bf71C80BD70034949a1";
    private static final String ADDRESS_FOR_PROFESSIONAL = "0x90B4F43b617bE3A5D947389921EE25f1f7c39A07";
    private static final int LIMIT_USERS = 3;

    @Autowired
    private HelperService helperService;

    @Autowired
    private TransactionHistoryRepository historyRepository;

    @Autowired
    private NewbieRepository newbieRepository;

    @Autowired
    private ExperiencedRepository experiencedRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private LuckyGamesService luckyGamesService;

    /**
     * {@inheritDoc}.
     */
    @Override
    public synchronized void sendTransaction(String address, Ether ether) throws InterruptedException, ExecutionException, TransactionTimeoutException {
        final TransactionHelper helper = helperService.getHelper();
        Web3j webThreeJ = Web3j.build(new HttpService());
        BigInteger privateKey = new BigInteger(helper.getPrivateKey());

        ECKeyPair ecKeyPair = ECKeyPair.create(privateKey.toByteArray());
        Credentials credentials = Credentials.create(ecKeyPair);

        BigDecimal value = Convert.toWei(ether.toString(), Convert.Unit.ETHER);
        Transfer.sendFundsAsync(webThreeJ, credentials, address, value, Convert.Unit.WEI).get();
    }

    @Override
    public void listeningTransaction() {
        Web3j webJ = Web3j.build(new HttpService());
        System.out.println("Waiting for transactions...");
        webJ.transactionObservable()
                .subscribe(t -> {
                    switch (t.getTo()) {
                        case ADDRESS_FOR_NEWBIE:
                            saveAddress(t.getFrom(),
                                    Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString(), t.getHash());
                            saveNewbieTransactions(t.getFrom());
                            break;
                        case ADDRESS_FOR_EXPERIENCED:
                            saveAddress(t.getFrom(),
                                    Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString(), t.getHash());
                            saveExperiencedTransactions(t.getFrom());
                            break;
                        case ADDRESS_FOR_PROFESSIONAL:
                            saveAddress(t.getFrom(),
                                    Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString(), t.getHash());
                            saveProfessionalTransactions(t.getFrom());
                            break;
                        default:
                            break;
                    }
                    System.out.println("To = " + t.getTo() + " From = " + t.getFrom()
                            + " hash = " + t.getHash() + " ETH = "
                            + Convert.fromWei(new BigDecimal(t.getValue()), Convert.Unit.ETHER).toString());
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
        history.setDate(LocalDateTime.now());
        historyRepository.save(history);
    }

    /**
     * @param address is going to be save in database.
     */
    private void saveNewbieTransactions(String address) {
        val newbie = new Newbie();
        newbie.setAddress(address);
        final Long id = newbieRepository.save(newbie).getId();
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
        final Long id = experiencedRepository.save(experienced).getId();
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
        final Long id = professionalRepository.save(professional).getId();
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
        final List<Newbie> newbies = newbieRepository.getBetweenId(from, to);
        List<String> addressList = new LinkedList<>();
        newbies.forEach(s -> addressList.add(s.getAddress()));
        luckyGamesService.newbieTransaction(addressList);
    }

    /**
     * Find users from table experienced between given id and make their happy.
     */
    private void sendExperienced(Long from, Long to) {
        final List<Experienced> experienced = experiencedRepository.getBetweenId(from, to);
        List<String> addressList = new LinkedList<>();
        experienced.forEach(s -> addressList.add(s.getAddress()));
        luckyGamesService.experiencedTransaction(addressList);
    }

    /**
     * Find users from table professional between given id and make their happy.
     */
    private void sendProfessional(Long from, Long to) {
        final List<Professional> professionals = professionalRepository.getBetweenId(from, to);
        List<String> addressList = new LinkedList<>();
        professionals.forEach(s -> addressList.add(s.getAddress()));
        luckyGamesService.professionalTransaction(addressList);
    }
}
