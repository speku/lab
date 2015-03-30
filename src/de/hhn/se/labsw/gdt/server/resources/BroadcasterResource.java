package de.hhn.se.labsw.gdt.server.resources;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.media.sse.SseBroadcaster;

import de.hhn.se.labsw.gdt.library.*;


@Singleton
@Path("broadcasts")
public final class BroadcasterResource {

    private static SseBroadcaster broadcaster = new SseBroadcaster();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public static String broadcastMessage(GameState gs) {
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder.name("modelEvent")
            .mediaType(MediaType.TEXT_PLAIN_TYPE)
            .data(GameState.class, gs)
            .build();

        broadcaster.broadcast(event);

        return "Model " + gs + " has been broadcast";
    }

    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput listenToBroadcast() {
        final EventOutput eventOutput = new EventOutput();
        this.broadcaster.add(eventOutput);
        return eventOutput;
    }
}