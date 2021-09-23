package ServerAdmin;



import Util.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("drones")
public class DroneServices {
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDronesList(){
        return Response.ok(DroneInfos.getInstance()).build();
    }


    @Path("add_drone")
    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    public Response addDrone(DroneInfo droneInfo){
        //List<DroneInfo> copy = DroneInfos.getInstance().getDronesList();
        //return  Response.status(Response.Status.GONE).build();
        boolean b = DroneInfos.getInstance().addDrone(droneInfo);
        if(!b)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        return Response.ok(new RegistrationMessage(
                DroneInfos.getInstance().getDronesList(),
                (int)(0 + Math.random() * Constants.GRID_DIMENSION),
                (int)(0 + Math.random() * Constants.GRID_DIMENSION))).build();
    }

    @Path("remove")
    @DELETE
    @Consumes({"application/json", "application/xml"})
    public Response removeDrone(DroneInfo droneInfo){
        DroneInfos.getInstance().removeDrone(droneInfo);
        return Response.ok().build();
    }


    @Path("add_stat")
    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    public Response addStats(GlobalStat stat){
        GlobalStats.getInstance().addStat(stat);
        return Response.ok().build();
    }

    @Path("get_stats")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getStatsList(){
        return Response.ok(GlobalStats.getInstance()).build();
    }

}
