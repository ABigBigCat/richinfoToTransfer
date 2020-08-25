package com.wugui.datax.admin.entity;

import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SshMod {

    //打印log日志
    private  final Logger logger = LoggerFactory.getLogger(Sftp.class);

    private  Date last_push_date = null;

    private Session sshSession;

    private ChannelSftp channel;

    private  ThreadLocal<Ftp> sftpLocal = new ThreadLocal<Ftp>();


    public  SshMod(String host, int port, String username, String password) throws Exception {
        JSch jsch = new JSch();
        jsch.getSession(username, host, port);
        //根据用户名，密码，端口号获取session
        sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
        sshSession.setConfig("userauth.gssapi-with-mic", "no");

        //为session对象设置properties,第一次访问服务器时不用输入yes
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect();
        //获取sftp通道
        channel = (ChannelSftp)sshSession.openChannel("sftp");
        channel.connect();
        logger.info("远程SSH连接" + host + "成功!" + sshSession);
    }


    public  Date getLast_push_date() {
        return last_push_date;
    }

    public  void setLast_push_date(Date last_push_date) {
       this.last_push_date = last_push_date;
    }

    public Session getSshSession() {
        return sshSession;
    }

    public void setSshSession(Session sshSession) {
        this.sshSession = sshSession;
    }

    public ChannelSftp getChannel() {
        return channel;
    }

    public void setChannel(ChannelSftp channel) {
        this.channel = channel;
    }

    public  ThreadLocal<Ftp> getSftpLocal() {
        return sftpLocal;
    }

    public  void setSftpLocal(ThreadLocal<Ftp> sftpLocal) {
        this.sftpLocal = sftpLocal;
    }
}
