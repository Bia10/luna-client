// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   signlink.java

package com.jagex.sign;

import io.luna.Resources;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.Applet;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public final class Signlink implements Runnable {

    public static String findcachedir() {
        Path cache = Resources.find("cache");

        return cache.toString() + "/";
    }

    public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

    public static boolean loginMusicOff = false;

    public static final void startpriv(InetAddress inetaddress) {
        threadliveid = (int) (Math.random() * 99999999D);
        if (active) {
            try {
                Thread.sleep(500L);
            } catch (Exception _ex) {
            }
            active = false;
        }
        socketreq = 0;
        threadreq = null;
        dnsreq = null;
        savereq = null;
        urlreq = null;
        socketip = inetaddress;
        Thread thread = new Thread(new Signlink());
        thread.setDaemon(true);
        thread.start();
        while (!active)
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
    }

    enum Position {
        LEFT,
        RIGHT,
        NORMAL
    }

    public static byte[] mac;
    public static int macAdd;

    @Override
    public final void run() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            //  mac = ni.getHardwareAddress();
            //   for (int i = 0; i < mac.length; i++) {
            // System.out.format("%02X%s", mac[i], (i < mac.length - 1) ?
            // "-" : "\n");
            // }
            // macAdd = byteArrayToInt(mac, 0);
            // System.out.println("Byte Array MAC Address Converted To Int:" +
            // macAdd);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        String s = findcachedir();
        active = true;
        uid = getuid();
        try {
            File file = new File(s + "main_file_cache.dat");
            if (file.exists() && file.length() > 0x3200000L)
                file.delete();
            cache_dat = new RandomAccessFile(s + "main_file_cache.dat", "rw");
            for (int j = 0; j < 5; j++)
                cache_idx[j] = new RandomAccessFile(s + "main_file_cache.idx" + j, "rw");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        for (int i = threadliveid; threadliveid == i; ) {
            if (socketreq != 0) {
                try {
                    socket = new Socket(socketip, socketreq);
                } catch (Exception _ex) {
                    socket = null;
                }
                socketreq = 0;
            } else if (threadreq != null) {
                Thread thread = new Thread(threadreq);
                thread.setDaemon(true);
                thread.start();
                thread.setPriority(threadreqpri);
                threadreq = null;
            } else if (dnsreq != null) {
                try {
                    dns = InetAddress.getByName(dnsreq).getHostName();
                } catch (Exception _ex) {
                    dns = "unknown";
                }
                dnsreq = null;
            } else if (savereq != null) {
                if (savebuf != null)
                    try (
                        FileOutputStream fileoutputstream = new FileOutputStream(s + savereq)) {
                        fileoutputstream.write(savebuf, 0, savelen);
                    } catch (Exception _ex) {
                    }
                if (waveplay) {
                    String wave = s + savereq;
                    try {
                        File sound = new File(wave);

                        if (sound.exists()) {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(sound));
                            clip.start();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    waveplay = false;
                }
                if (midiplay) {
                    midi = s + savereq;
                    try {
                        if (musicS != null && musicSr.isOpen()) {
                            musicSr.stop();
                            musicSr.close();
                        }

                        File music = new File(midi);

                        if (music.exists()) {
                            musicS = MidiSystem.getSequence(music);
                            musicSr = MidiSystem.getSequencer();
                            musicSr.open();
                            musicSr.setSequence(musicS);
                            musicSr.start();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    midiplay = false;
                }
                savereq = null;
            } else if (urlreq != null) {
                try {
                    urlstream = new DataInputStream((new URL(mainapp.getCodeBase(), urlreq)).openStream());
                } catch (Exception _ex) {
                    urlstream = null;
                }
                urlreq = null;
            }
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }

    }

    public static Sequencer musicSr = null;
    public Sequence musicS = null;

    private static final int getuid() {
        return (int) (Math.random() * 99999999D);
    }

    public static final synchronized Socket opensocket(int i) throws IOException {
        for (socketreq = i; socketreq != 0; )
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }

        if (socket == null)
            throw new IOException("could not open socket");
        return socket;
    }

    public static final synchronized DataInputStream openurl(String s) throws IOException {
        for (urlreq = s; urlreq != null; )
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }

        if (urlstream == null)
            throw new IOException("could not open: " + s);
        return urlstream;
    }

    public static final synchronized void dnslookup(String s) {
        dns = s;
        dnsreq = s;
    }

    public static final synchronized void startthread(Runnable runnable, int i) {
        threadreqpri = i;
        threadreq = runnable;
    }

    public static final synchronized boolean wavesave(byte abyte0[], int i) {
        if (i > 0x1e8480)
            return false;
        if (savereq != null) {
            return false;
        }
        wavepos = (wavepos + 1) % 5;
        savelen = i;
        savebuf = abyte0;
        waveplay = true;
        savereq = "sound" + wavepos + ".wav";
        return true;
    }

    public static final synchronized boolean wavereplay() {
        if (savereq != null) {
            return false;
        }
        savebuf = null;
        waveplay = true;
        savereq = "sound" + wavepos + ".wav";
        return true;
    }

    public static final synchronized void midisave(byte abyte0[], int i) {
        if (i > 0x1e8480)
            return;
        if (savereq != null) {
            return;
        }
        midipos = (midipos + 1) % 5;
        savelen = i;
        savebuf = abyte0;
        midiplay = true;
        savereq = "jingle" + midipos + ".mid";
        return;
    }

    private static Logger logger = Logger.getLogger("RS2Logger");

    public static final void reporterror(String s) {
        if (!reporterror)
            return;
        if (!active)
            return;
        logger.warning(s);
        s = s.replace(':', '_');
        s = s.replace('@', '_');
        s = s.replace('&', '_');
        s = s.replace('#', '_');

        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(openurl("reporterror" + 317 + ".cgi?error=" + errorname + " " + s)))) {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Signlink() {
    }

    public static int uid;
    public static int storeid = 32;
    public static RandomAccessFile cache_dat = null;
    public static RandomAccessFile cache_idx[] = new RandomAccessFile[5];
    public static boolean sunjava;
    public static Applet mainapp = null;
    private static boolean active;
    private static int threadliveid;
    private static InetAddress socketip;
    private static int socketreq;
    private static Socket socket = null;
    private static int threadreqpri = 1;
    private static Runnable threadreq = null;
    private static String dnsreq = null;
    public static String dns = null;
    private static String urlreq = null;
    private static DataInputStream urlstream = null;
    private static int savelen;
    private static String savereq = null;
    private static byte savebuf[] = null;
    private static boolean midiplay;
    private static int midipos;
    public static String midi = null;
    public static int midivol;
    public static int midifade;
    private static boolean waveplay;
    private static int wavepos;
    public static String wave = null;
    public static int wavevol;
    public static boolean reporterror = true;
    public static String errorname = "";

}
