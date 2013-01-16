package za.ac.uct.cs.rfsaws.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author James
 */
@ManagedBean
@SessionScoped
public class ServerBean {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;
    private String info;
    private String hostName;
    private int port;
    private boolean resolveAuctions;
    private Long resolveDelayMS;

    /**
     * Creates a new instance of ServerBean
     */
    public ServerBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        info = request.getServletContext().getServerInfo();
        hostName = request.getServerName();
        port = request.getServerPort();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getPrimaryNodeCount() {
        return (Long) em.createQuery("SELECT COUNT(n) FROM PrimaryUserNodeEntity n").getSingleResult();
    }

    public long getSecondaryNodeCount() {
        return (Long) em.createQuery("SELECT COUNT(n) FROM SecondaryUserNodeEntity n").getSingleResult();
    }

    public long getActiveLeaseCount() {
        return (Long) em.createQuery("SELECT COUNT(n) FROM LeaseEntity n "
                + "WHERE CURRENT_TIMESTAMP BETWEEN n.dateStart AND n.dateEnd").getSingleResult();
    }

    public long getActiveAuctionCount() {
        return (Long) em.createQuery("SELECT COUNT(n) FROM AuctionEntity n "
                + "WHERE CURRENT_TIMESTAMP BETWEEN n.auctionStart AND n.auctionEnd").getSingleResult();
    }

    public long getAvailbableAllocationsCount() {
        return (Long) em.createQuery("SELECT COUNT(n) FROM AllocationEntity n WHERE CURRENT_TIMESTAMP BETWEEN n.beginDate AND n.endDate").getSingleResult();
    }

    public long getAvailableSegmentCount() {
        return (Long) em.createQuery("SELECT COUNT(n) FROM SegmentEntity n").getSingleResult();
    }

    public long getActiveBidCount() {
        return (Long) em.createQuery("SELECT COUNT(b) FROM BidEntity b WHERE b.auction.id IN (SELECT n.id FROM AuctionEntity n WHERE CURRENT_TIMESTAMP BETWEEN n.auctionStart AND n.auctionEnd)").getSingleResult();
        //return 0;
    }
}
