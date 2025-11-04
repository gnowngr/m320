package ch.tbz.gino_goncalo.M4_Gonçalo.model;

/**
 * Enum fuer Account Limits
 * Clean Code: Enums statt Magic Numbers
 */
public enum AccountLimit {
    GIRO_OVERDRAFT(1000.0),
    DAILY_WITHDRAWAL_LIMIT(5000.0),
    MONTHLY_TRANSFER_LIMIT(50000.0),
    MAX_TRANSACTION_AMOUNT(100000.0);

    private final double limit;

    AccountLimit(double limit) {
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }
}
