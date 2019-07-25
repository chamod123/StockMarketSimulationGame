package Messages;

import Model.Account;
import Model.Transaction;

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


}
