package ServerAdmin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdminServices {

    @Path("get_drones")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDronesList(){
        return Response.ok(DroneInfos.getInstance()).build();
    }

    @Path("get_stats")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getStatsList(){
        return Response.ok(GlobalStats.getInstance()).build();
    }

    @Path("get_stats/{number}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getLastStats(@PathParam("number") int n){
        if(n<=0) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(GlobalStats.getInstance().getLastStats(n)).build();
    }

    @Path("get_average_delivery/{t1}/{t2}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getAverageDelivery(@PathParam("t1") long t1,@PathParam("t2") long t2){
        if(t1>t2) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(GlobalStats.getInstance().getAverageDelivery(t1,t2)).build();
    }

    @Path("get_average_km/{t1}/{t2}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getAverageKm(@PathParam("t1") long t1,@PathParam("t2") long t2){
        if(t1>t2) return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(GlobalStats.getInstance().getAverageKm(t1,t2)).build();
    }




}
