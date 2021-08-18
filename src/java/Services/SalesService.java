
package Services;

import Model.Sales;
import Dao.SalesDao;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;

@Path("/sales")
public class SalesService {
    
    SalesDao salesDao = new SalesDao();
    
    @GET
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sales> getSalesByUser (@PathParam("user") String userName) {
        return salesDao.GetSalesByUser(userName);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sales> getAllSales () {
        return salesDao.GetAllSales();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Sales insertSale (Sales sale) {
        return salesDao.InsertSale(sale);
    }
    
    @PUT
    @Path("/{idsale}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Sales updateSale (@PathParam("idsale") int id, Sales sale) {
        sale.setId(id);
        return salesDao.UpdateSale(sale);
    }
    
    @DELETE
    @Path("/{idsale}")
    public int deleteSale (@PathParam("idsale") int id) {
        return salesDao.DeleteSale(id);
    }

    /*@Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }*/
}
