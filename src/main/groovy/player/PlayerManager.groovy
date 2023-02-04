package player

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import net.dv8tion.jda.api.entities.Guild
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

// https://github.com/sedmelluq/lavaplayer/blob/master/demo-jda/src/main/java/com/sedmelluq/discord/lavaplayer/demo/jda/Main.java
class PlayerManager {

    private final static musicManagers = new HashMap<String, GuildPlayer>()
    private static final playerManager = new DefaultAudioPlayerManager()

    public static void play(MessageReceivedEvent event, String trackUrl) {
        def guild = event.getGuild()
        def musicManager = getGuildAudioPlayer(guild)
        connectToVoiceChannel(event)
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {

            @Override
            void trackLoaded(AudioTrack track) {
                play(guild, track)
            }

            @Override
            void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack()

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0)
                }

                play(guild, firstTrack)
            }

            @Override
            void noMatches() {
                notifyAboutError(guild, "Nothing found by $trackUrl")
            }

            @Override
            void loadFailed(FriendlyException exception) {
                notifyAboutError(guild, "Could not play: $exception")
            }

        })
    }

    private static GuildPlayer getGuildAudioPlayer(Guild guild) {
        def guildId = Long.parseLong(guild.getId())
        def musicManager = musicManagers.get(guildId)

        if (musicManager == null) {
            musicManager = new GuildPlayer(playerManager)
            musicManagers.put(guildId, musicManager)
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler())

        return musicManager
    }

    private static void notifyAboutError(MessageReceivedEvent event, String message) {
        event.getChannel().sendMessage(message).queue()
    }

    private static void connectToVoiceChannel(MessageReceivedEvent event) {
        def audioManager = event.getGuild().getAudioManager()

        if (!event.getMember().getVoiceState().inAudioChannel()) {
            notifyAboutError(event, 'You need to be in a voice channel')
            return
        }

        def voiceChannel = event.getMember().getVoiceState().getChannel()
        audioManager.openAudioConnection(voiceChannel)
    }

}
