package edu.upc.dsa.services;
import edu.upc.dsa.*;
import edu.upc.dsa.exceptions.ItemNotFoundException;
import edu.upc.dsa.exceptions.UserNotLoggedInException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/items", description = "Endpoint to Items Service with Data Base")
@Path("/items")
public class ItemServiceBBDD {
    private ItemManager im;
    private StoreManager sm;
    private UserManager um;
    private CharacterManager cm;
    private SessionManager sesm; //per les galetes
    final static Logger logger = Logger.getLogger(ItemServiceBBDD.class);
    public ItemServiceBBDD() {
        this.im = new ItemManagerImplBBDD();
        this.sm = StoreManagerImplBBDD.getInstance();
        this.um = UserManagerImplBBDD.getInstance();
        this.cm = CharacterManagerImplBBDD.getInstance();
        this.sesm = SessionManager.getInstance();

    }
    @DELETE
    @ApiOperation(value = "delete an Item", notes = "hello")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found"),
            @ApiResponse(code = 500, message = "User not logged in")
    })
    @Path("/{ItemName}")
    public Response deleteItem(@PathParam("ItemName") String ItemName, @CookieParam("authToken") String authToken) {
        try{
            sesm.getSession(authToken);
            this.im.deleteItem(ItemName);
            return Response.status(201).build();
        }
        catch(ItemNotFoundException ex){
            return Response.status(404).build();
        }
        catch(UserNotLoggedInException ex){
            logger.warn("Attention, User not yet logged in");
            return Response.status(500).build();
        }
    }

    @GET
    @ApiOperation(value = "get all Items", notes = "hahaha")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "User not logged in")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(@CookieParam("authToken") String authToken) {
        try{
            List<Item> items = this.im.findAll();
            //TREUREHO ES UN TEST
            User u=SessionManager.getInstance().getSession(authToken);
            System.out.println(u.getName());
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
            return Response.status(201).entity(entity).build()  ;
        }
        catch(UserNotLoggedInException ex){
            logger.warn("User not yet logged in");
            return Response.status(500).build();
        }

    }

    @GET
    @ApiOperation(value = "get an Item", notes = "hahaha")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "Track not found"),
            @ApiResponse(code = 500, message = "User not logged in")

    })
    @Path("/{ItemName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("ItemName") String ItemName, @CookieParam("authToken") String authToken) {
        try{
            this.sesm.getSession(authToken);
            Item i = this.im.getItem(ItemName);
            return Response.status(201).entity(i).build();
        }
        catch(ItemNotFoundException ex){
            return Response.status(404).build();
        }
        catch(UserNotLoggedInException ex){
            logger.warn("User not yet logged in");
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "Add a new Item", notes = "hello")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Item.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 501, message = "User not logged in")


    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddNewItem(Item i, @CookieParam("authToken") String authToken) {
        if (i.getName()==null)  return Response.status(500).build();
        try{
            this.sesm.getSession(authToken);
            im.addItem(i);
            return Response.status(201).entity(i).build();
        }
        catch(UserNotLoggedInException ex){
            logger.warn("User not yet logged in");
            return Response.status(500).build();
        }
    }

    @PUT
    @ApiOperation(value = "Aplica descuento/modifica Item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found"),
            @ApiResponse(code = 500, message = "User not logged in")
    })
    @Path("/")
    public Response updateItem(Item item, @CookieParam("authToken") String authToken) {
        try{
            this.sesm.getSession(authToken);
            Item i = this.im.updateItem(item);
            return Response.status(201).build();
        }
        catch(ItemNotFoundException ex){
            return Response.status(404).build();
        }
        catch(UserNotLoggedInException ex){
            logger.warn("User not yet logged in");
            return Response.status(500).build();
        }
    }
}
