package pw.mihou.amelia.commands.test;

import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import pw.mihou.amelia.Amelia;
import pw.mihou.amelia.commands.base.Command;
import pw.mihou.amelia.commands.db.FeedDB;
import pw.mihou.amelia.io.rome.ReadRSS;
import pw.mihou.amelia.templates.Message;

import java.util.ArrayList;
import java.util.Optional;

public class TestCommand extends Command {

    public TestCommand() {
        super("test", "Test runs a feed.", "test", true);
    }

    @Override
    protected void runCommand(MessageCreateEvent event, User user, Server server, String[] args) {
        // Add more security.
        if (event.getMessageAuthor().isServerAdmin() || server.canCreateChannels(user) || server.canManage(user) || server.canManageRoles(user)) {
            if (args.length > 1) {
                try {
                    long id = Long.parseLong(args[1]);
                    if (FeedDB.validate(id)) {
                        event.getMessage().reply("Please wait...").thenAccept(msg -> FeedDB.getServer(server.getId()).getChannel(event.getChannel().getId()).getFeedModel(id).ifPresentOrElse(feedModel ->
                                        ReadRSS.getLatest(feedModel.getFeedURL()).ifPresentOrElse(syndEntry ->
                                                        server.getTextChannelById(feedModel.getChannel()).ifPresentOrElse(tc ->
                                                                Message.msg(Amelia.format(syndEntry, feedModel, server)).send(tc)
                                                                        .whenComplete((message, throwable) -> Optional.ofNullable(throwable).ifPresentOrElse(t -> msg.delete().thenAccept(unused ->
                                                                                        event.getMessage().reply("Error: A throwable was thrown, the bot possibly cannot send a message to the channel.")),
                                                                                () -> msg.delete().thenAccept(u -> event.getMessage().reply("The test went well!")))),
                                                                () -> msg.delete().thenAccept(u -> event.getMessage().reply("Error: The channel provided does not exist."))),
                                                () -> msg.delete().thenAccept(unused -> msg.delete().thenAccept(u -> event.getMessage().reply("Error: We couldn't connect to ScribbleHub's RSS feed, please try again later.")))),
                                () -> msg.delete().thenAccept(unused -> event.getMessage().reply("We couldn't find the feed, are you sure you are using the feed's unique ID?"))));
                    } else {
                        event.getMessage().reply("Error: We couldn't find the feed, are you sure you are using the feed's unique id." +
                                "\nPlease verify using `feeds`");
                    }
                } catch (NumberFormatException | ArithmeticException e) {
                    event.getMessage().reply("Error: Number format exception, or arithmetic exception.");
                }
            } else {
                event.getMessage().reply("Error: Lacking arguments [feed id]");
            }
        }
    }

    private String getMentions(ArrayList<Long> roles, Server server) {
        StringBuilder builder = new StringBuilder();
        roles.forEach(aLong -> builder.append(server.getRoleById(aLong).map(Role::getMentionTag).orElse("[Vanished Role]")));
        return builder.toString();
    }
}
