package database.dao.util.query;

public final class CommonQuery {
    private CommonQuery() {
    }
    public final static String SELECT_ALL_AUDIO =            "select audiotrack.*,artist.pseudonymArtist from audiotrack inner join artist on artist.idArtist=audiotrack.idArtist";
    public final static String SELECT_AUDIOTRACK_OF_ARTIST = "select audiotrack.*, artist.pseudonymArtist from artist, audiotrack where audiotrack.idArtist=artist.idArtist and artist.pseudonymArtist = ?";
    public final static String SELECT_AURIOTRACK_WITH_NAME = " select audiotrack.*, artist.pseudonymArtist from audiotrack, artist where nameTrack like(?)";
    public final static String SELECT_TRACKS_FROM_PACKAGE = "select audiotrack.*, artist.pseudonymArtist " +
            "from artist,  audiotrack " +
            "where artist.idArtist=audiotrack.idArtist and audiotrack.idAudioTrack in( select packagetrack.AudioTrack_idAudioTrack from packagetrack,  package where packagetrack.Package_idPackage=package.idPackage and package.namePackage like(?))";


    public final static String SELECT_ARTIST_USING_NAME = "select * from artist where artist.pseudonymArtist = ?";
    public final static String SELECT_MOST_ARTIST = "select * from artist;";


    public final static String SELECT_ALL_PACKAGES = "select * from package";
    public final static String SELECT_PACKAGE_USIN_NAME = "select * from package where namePackage = ?";


    public final static String SELECT_ALBUM_USING_NAME = "select * from album where nameAlbum = ?";
    public final static String SELECT_ALBUM_BY_ARTIST = "select album.idAlbum , album.nameAlbum,album.Artist_idArtist from album,artist where album.Artist_idArtist=artist.idArtist and artist.pseudonymArtist=?";


    public final static String SIGN_IN_CLIENT = "select * from client where loginClient = ? and passwordClient = ?";
    public final static String CHANGE_PASSWORD = "update client set passwordClient = ? where loginClient = ? and passwordClient = ?";
}
