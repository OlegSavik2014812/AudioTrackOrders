package controller.command;

import controller.command.impl.admin.*;
import controller.command.impl.client.CheckClientBalance;
import controller.command.impl.client.GetClientPurchases;
import controller.command.impl.client.MakePurchase;
import controller.command.impl.client.SignUpClient;
import controller.command.impl.common.*;
import resource.CommandName;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final static CommandProvider INSTANCE = new CommandProvider();
    private final Map<CommandName, Command> commandStore;


    private CommandProvider() {
        commandStore = new HashMap<>();


        //admin
        commandStore.put(CommandName.ADD_ALBUM, new AddAlbum());
        commandStore.put(CommandName.ADD_ARTIST, new AddArtist());
        commandStore.put(CommandName.ADD_AUDIOTRACK_ALBUM, new AddAlbumAudioTrack());
        commandStore.put(CommandName.ADD_AUDIOTRACK_SINGLE, new AddSingleAudioTrack());
        commandStore.put(CommandName.ADD_PACKAGE, new AddPackage());
        commandStore.put(CommandName.ADD_PACKAGETRACK, new AddPackageTrack());
        commandStore.put(CommandName.CHANGE_CLIENT_BONUS, new ChangeClientBonus());
        commandStore.put(CommandName.DELETE_TRACK_FROM_PACKAGE, new DeleteTrackFromPackage());
        commandStore.put(CommandName.GET_ALL_CLIENTS, new GetAllClients());
        commandStore.put(CommandName.UPDATE_AUDIOTRACK_PRICE, new UpdateAudioTrackPrice());
        commandStore.put(CommandName.UPDATE_PACKAGE_NAME, new UpdatePackageName());
        commandStore.put(CommandName.SET_MONEY_TO_CLIENT, new SetMoneyToClient());
        commandStore.put(CommandName.GET_ALL_PURCHASES, new GetAllPurchases());

        //client
        commandStore.put(CommandName.MAKE_PURCHASE, new MakePurchase());
        commandStore.put(CommandName.SIGN_UP_CLIENT, new SignUpClient());
        commandStore.put(CommandName.GET_CLIENT_PURCHASES, new GetClientPurchases());
        commandStore.put(CommandName.CHECK_CLIENT_BALANCE, new CheckClientBalance());


        //common
        commandStore.put(CommandName.CHANGE_PASSOWORD, new ChangeClientPassword());
        commandStore.put(CommandName.GET_ALBUM_USING_NAME, new GetAlbumUsingName());
        commandStore.put(CommandName.GET_ALBUMS_OF_ARTIST, new GetAlbumsOfArtist());
        commandStore.put(CommandName.GET_ALL_ARTIST, new GetAllArtist());
        commandStore.put(CommandName.GET_ALL_AUDIOTRACKS, new GetAllAudioTracks());
        commandStore.put(CommandName.GET_ALL_PACKAGES, new GetAllPackages());
        commandStore.put(CommandName.GET_ARTIST_USING_NAME, new GetArtistUsingName());
        commandStore.put(CommandName.GET_AUDIOTRACK_OF_ARTIST, new GetAudioTrackOfArtist());
        commandStore.put(CommandName.GET_AUDIOTRACK_OF_PACKAGE, new GetAudioTrackOfPackage());
        commandStore.put(CommandName.GET_AUDIOTRACK_USING_NAME, new GetAudioTrackUsingName());
        commandStore.put(CommandName.SIGN_IN, new SignIn());
    }


    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commandStore.get(commandName);
    }
}
