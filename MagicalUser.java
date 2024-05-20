
    public abstract class MagicalUser<T extends Account> {
        protected T account;

        public MagicalUser(T account) {
            this.account = account;
        }

        public T getAccount() {
            return account;
        }

        public void setAccount(T account) {
            this.account = account;
        }
    }

