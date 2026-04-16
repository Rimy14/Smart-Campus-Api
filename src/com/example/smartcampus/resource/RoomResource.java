package com.example.smartcampus.resource;

import com.example.smartcampus.model.Room;
import com.example.smartcampus.store.DataStore;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    @GET
    public Collection<Room> getRooms() {
        return DataStore.rooms.values();
    }

    @GET
    @Path("/{id}")
    public Response getRoomById(@PathParam("id") int id) {
        Room room = DataStore.rooms.get(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(room).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room, @Context UriInfo uriInfo) {

        if (room == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Request body is missing or invalid JSON")
                    .build();
        }

        int id = DataStore.roomIdCounter++;
        room.setId(id);

        DataStore.rooms.put(id, room);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(String.valueOf(id));

        return Response.created(builder.build()).entity(room).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") int id) {

        Room room = DataStore.rooms.get(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (room.getCapacity() > 0) {
            throw new com.example.smartcampus.exception.RoomNotEmptyException("Room has active sensors");
        }

        DataStore.rooms.remove(id);

        return Response.noContent().build();
    }
}