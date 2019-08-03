package Messages;


import Model.Account;
import Model.Player;
import Model.Transaction;
import akka.actor.ActorRef;

import java.io.Serializable;

public interface PlayerMessages {

    class ActionPerformed implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String description;

        public ActionPerformed(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    class CreatePlayerMessage implements Serializable {

        private static final long serialVersionUID = 1L;
        private final Player player;
        private static ActorRef bankActor;

        public CreatePlayerMessage(Player player, ActorRef bankActor) {
            this.player = player;
            this.bankActor = bankActor;
        }

        public Player getPlayer() {
            return player;
        }

        public ActorRef getBankActor() {
            return bankActor;
        }
    }

    class GetPlayerMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Long playerId;

        public GetPlayerMessage(Long playerId) {
            this.playerId = playerId;
        }

        public Long getPlayerId() {
            return playerId;
        }
    }

    class LoginPlayerMessage implements Serializable {
        private final String userName;
        private final String password;

        public LoginPlayerMessage(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }


    class AddPlayerToGameMessage implements Serializable {
        private final Long id;
        private static ActorRef brokerActor;

        public AddPlayerToGameMessage(Long id, ActorRef brokerActor) {
            this.id = id;
            this.brokerActor = brokerActor;
        }

        public Long getId() {
            return id;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }
    }

    class UpdatePlayerMessage implements Serializable {
        private final Player player;

        public UpdatePlayerMessage(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }

    }

    class WithdrawMessage implements Serializable {
        private static ActorRef bankActor;
        private final Transaction transaction;

        public WithdrawMessage(Transaction transaction, ActorRef bankActor) {
            this.transaction = transaction;
            this.bankActor = bankActor;
        }

        public static ActorRef getBankActor() {
            return bankActor;
        }

        public Transaction getTransaction() {
            return transaction;
        }
    }

    class DepoditMessage implements Serializable {
        private static ActorRef bankActor;
        private final Transaction transaction;

        public DepoditMessage(Transaction transaction, ActorRef bankActor) {
            this.transaction = transaction;
            this.bankActor = bankActor;
        }

        public static ActorRef getBankActor() {
            return bankActor;
        }

        public Transaction getTransaction() {
            return transaction;
        }
    }


    //update Account
    class UpdateAccountMessage implements Serializable {
        private final Account account;
        private static ActorRef bankActor;

        public UpdateAccountMessage(Account account,ActorRef bankActor) {
            this.account = account;
            this.bankActor=bankActor;
        }

        public Account getAccount() {
            return account;
        }

        public static ActorRef getBankActor() {
            return bankActor;
        }
    }


}