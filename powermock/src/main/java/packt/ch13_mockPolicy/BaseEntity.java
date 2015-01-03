package packt.ch13_mockPolicy;

/**
 * The base class which
 * is going to do things
 * that would make its derived classes difficult to test.
 *
 * @author Deep Shah
 */
public class BaseEntity {

    /**
     * Static initializer that will throw a NullPointerException.
     */
    static {
        String x = null;
        x.toString();
    }

    /**
     * This method is responsible to do write audit trails.
     * Currently this method throws an UnsupportedOperationException.
     *
     * @param auditInformation the audit information to log.
     */
    protected void performAudit(String auditInformation) {
        throw new UnsupportedOperationException();
    }
}
