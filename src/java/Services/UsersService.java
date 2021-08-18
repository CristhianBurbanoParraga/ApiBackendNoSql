package Services;

import Model.Users;
import Dao.UsersDao;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

@Path("/users")
public class UsersService implements ContainerResponseFilter {

    UsersDao usersDao = new UsersDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getAllUsers() {
        return usersDao.GetAllUsers();
    }

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUserByCredentialsLogin(@QueryParam("user") String user, @QueryParam("password") String pass) {
        return usersDao.GetUserByCredentialsLogin(user, pass);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Users insertUser(Users user) {
        return usersDao.InsertUser(user);
    }

    @PUT
    @Path("/{iduser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Users updateUser(@PathParam("iduser") int id, Users user) {
        user.setId(id);
        return usersDao.UpdateUser(user);
    }

    @DELETE
    @Path("/{iduser}")
    public int deleteUser(@PathParam("iduser") int id) {
        return usersDao.DeleteUser(id);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, 
            ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
