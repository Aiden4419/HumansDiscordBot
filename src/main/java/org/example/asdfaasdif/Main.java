package org.example.asdfaasdif;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.Route;

import java.util.UUID;

public class Main implements NativeKeyListener {
    public static JDA jda;
    public static void main(String[] args) throws NativeHookException {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("BOT_TOKEN");
        if (token == null) {
            System.out.println("Token is null");
            System.exit(0);
        }
        JDABuilder builder = JDABuilder.createLight(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MODERATION);


        jda = builder.build();


        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new Main());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Button Pressed");
        if (e.getKeyCode() == NativeKeyEvent.VC_O) {
            System.out.println("Button Pressed O");
            Guild ea = jda.getGuildById("1175545348228399114");
            ea.getMemberById("1152913634117304330").deafen(true).queue();
        }
    }
    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Button Released");
        if (e.getKeyCode() == NativeKeyEvent.VC_O) {
            System.out.println("Button Released O");
            Guild ea = jda.getGuildById("1175545348228399114");
            System.out.println(ea);
            ea.getMemberById("1152913634117304330").deafen(false).queue();
        }
    }
}