package Messages;

import Model.Account;
import Model.Transaction;
import akka.actor.ActorRef;

import java.io.Serializable;

public interface BankMessages {

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

    class CreateAccountMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Account account;

        public CreateAccountMessage(Account account) {
            this.account = account;
        }

        public Account getAccount() {
            return account;
        }
    }

    class WithdrawMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Transaction transaction;

        public WithdrawMessage(Transaction transaction) {
            this.transaction = transaction;
        }

        public Transaction getTransaction() {
            return transaction;
        }

    }

    class DepositMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Transaction transaction;

        public DepositMessage(Transaction transaction) {
            this.transaction = transaction;
        }

        public Transaction getTransaction() {
            return transaction;
        }
    }

    //get Bank Balance for a player
    class GetBankBalanceMessage implements Serializable {
        private final String name;

        public GetBankBalanceMessage(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    //get Bank Balance for all player
    class GetAllBankBalanceMessage implements Serializable {

        public GetAllBankBalanceMessage() {
        }

    }

    //update Account
    class UpdateAccountMessage implements Serializable {
        private final Account account;

        public UpdateAccountMessage(Account account) {
            this.account = account;
        }

        public Account getAccount() {
            return account;
        }
    }


}
