
package Connection;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class ConnectionCassandra {
    private Cluster cluster;
    private Session session;
    
    public void ConnectCassandra(String seed, int port) {
        this.cluster = Cluster.builder().addContactPoint(seed)
                .withPort(port).build();
        final Metadata metada = cluster.getMetadata();
        for (final Host host : metada.getAllHosts()) {
            System.out.println("Driver Version: " + host.getCassandraVersion());
        }
        this.session = cluster.connect();
    }
    
    public Session GetSession() {
        return this.session;
    }
    
    public void CloseConnect () {
        cluster.close();
    }
}
