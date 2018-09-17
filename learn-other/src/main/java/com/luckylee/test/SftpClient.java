package com.luckylee.test;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 利用JSch包实现SFTP下载、上传文件的类
 * <h3>example</h3>
 * <pre class="code">
 *         SftpClient client = null;
 *         ChannelSftp sftp = null;
 *         try {
 *             client = SftpClient.builder()
 *                     .enableProxy(true)
 *                     .proxyHost("11.8.44.60")
 *                     .proxyPort(3128)
 *                     .host("11.8.44.146")
 *                     .port(22)
 *                     .username("wasadm")
 *                     .password("ect@wasadm")
 *                     .build();
 *             sftp = client.connect();
 *             int fileCount=client.download(sftp, "/ztsoft/usr/wasadm/batch/file/20180910", "/Users/lining/billFile", new String[]{});
 *             client.upload(sftp, "/Users/lining/billFile", "/ztsoft/usr/wasadm/batch/file/20180810");
 *         } catch (Exception e) {
 *             e.printStackTrace();
 *         } finally {
 *             if(client!=null){
 *                client.disconnect(sftp);
 *             }
 *
 *         }
 * </pre>
 *
 * @version 1.0
 */
public class SftpClient {
    private static final Logger logger = LoggerFactory.getLogger(SftpClient.class);

    private static final String SFTP_PROTOCOL = "sftp";

    private String host;

    private int port;

    private String username;

    private String password;

    private boolean enableAuthWithPassword;

    private int loginConnectionTimeOut;

    private int createChannelConnectionTimeOut;

    private boolean enableProxy;

    private String proxyHost;

    private int proxyPort;

    private boolean enableAuthWithKey;

    private String privateKey;

    private String passphrase;

    private SftpClient(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.enableAuthWithPassword = builder.enableAuthWithPassword;
        this.loginConnectionTimeOut = builder.loginConnectionTimeOut;
        this.createChannelConnectionTimeOut = builder.createChannelConnectionTimeOut;
        this.enableProxy = builder.enableProxy;
        this.proxyHost = builder.proxyHost;
        this.proxyPort = builder.proxyPort;
        this.enableAuthWithKey = builder.enableAuthWithKey;
        this.privateKey = builder.privateKey;
        this.passphrase = builder.passphrase;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnableAuthWithPassword() {
        return enableAuthWithPassword;
    }

    public long getLoginConnectionTimeOut() {
        return loginConnectionTimeOut;
    }

    public long getCreateChannelConnectionTimeOut() {
        return createChannelConnectionTimeOut;
    }

    public boolean isEnableProxy() {
        return enableProxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public boolean isEnableAuthWithKey() {
        return enableAuthWithKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public static class Builder {
        private String host;
        private int port;
        private String username;
        private String password;
        private boolean enableAuthWithPassword = true;
        private int loginConnectionTimeOut = 15000;
        private int createChannelConnectionTimeOut = 3000;
        private boolean enableProxy = false;
        private String proxyHost;
        private int proxyPort;
        private boolean enableAuthWithKey = false;
        private String privateKey;
        private String passphrase;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder enableAuthWithPassword(boolean enableAuthWithPassword) {
            this.enableAuthWithPassword = enableAuthWithPassword;
            return this;
        }

        public Builder loginConnectionTimeOut(int loginConnectionTimeOut) {
            this.loginConnectionTimeOut = loginConnectionTimeOut;
            return this;
        }

        public Builder createChannelConnectionTimeOut(int createChannelConnectionTimeOut) {
            this.createChannelConnectionTimeOut = createChannelConnectionTimeOut;
            return this;
        }

        public Builder enableProxy(boolean enableProxy) {
            this.enableProxy = enableProxy;
            return this;
        }

        public Builder proxyHost(String proxyHost) {
            this.proxyHost = proxyHost;
            return this;
        }

        public Builder proxyPort(int proxyPort) {
            this.proxyPort = proxyPort;
            return this;
        }

        public Builder enableAuthWithKey(boolean enableAuthWithKey) {
            this.enableAuthWithKey = enableAuthWithKey;
            return this;
        }

        public Builder privateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder passphrase(String passphrase) {
            this.passphrase = passphrase;
            return this;
        }

        public SftpClient build() {
            return new SftpClient(this);
        }
    }

    public ChannelSftp connect() throws Exception {
        if (enableAuthWithPassword) {
            return connect(host, username, password, port);
        }
        if (enableAuthWithKey) {
            return connect(username, host, port, privateKey, passphrase);
        }
        return null;
    }

    /**
     * Password authorization
     *
     * @param host     主机IP
     * @param username 主机登陆用户名
     * @param password 主机登陆密码
     * @param port     主机ssh登陆端口，如果port <= 0取默认值(22)
     * @return sftp
     * @throws Exception
     */
    public ChannelSftp connect(String host, String username, String password, int port) throws Exception {
        JSch jsch = new JSch();

        Session session = createSession(jsch, host, username, port);
        // 设置登陆主机的密码
        session.setPassword(password);
        // 设置登陆超时时间
        session.connect(loginConnectionTimeOut);
        return getSftp(session, host);
    }

    /**
     * Private/public key authorization (加密秘钥方式登陆)
     *
     * @param username   主机登陆用户名(user account)
     * @param host       主机IP(server host)
     * @param port       主机ssh登陆端口(ssh port), 如果port<=0, 取默认值22
     * @param privateKey 秘钥文件路径(the path of key file.)
     * @param passphrase 密钥的密码(the password of key file.)
     * @return sftp
     * @throws Exception
     */
    public ChannelSftp connect(String username, String host, int port, String privateKey, String passphrase)
            throws Exception {
        JSch jsch = new JSch();

        // 设置密钥和密码 ,支持密钥的方式登陆
        if (StringUtils.isNotEmpty(privateKey)) {
            if (StringUtils.isNotEmpty(passphrase)) {
                // 设置带口令的密钥
                jsch.addIdentity(privateKey, passphrase);
            } else {
                // 设置不带口令的密钥
                jsch.addIdentity(privateKey);
            }
        }
        Session session = createSession(jsch, host, username, port);
        // 设置登陆超时时间
        session.connect(loginConnectionTimeOut);
        return getSftp(session, host);
    }

    private ChannelSftp getSftp(Session session, String host) {
        ChannelSftp sftp = null;
        try {
            // 创建sftp通信通道
            logger.info("Session connected to " + host + ".");
            Channel channel = session.openChannel(SFTP_PROTOCOL);
            channel.connect(createChannelConnectionTimeOut);
            logger.info("Channel created to " + host + ".");
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            logger.warn("exception when channel create.", e);
        }
        return sftp;
    }

    /**
     * upload all the files to the server<br/>
     * 将本地文件名为 srcFile 的文件上传到目标服务器, 目标文件名为 dest,<br/>
     * 若 dest为目录，则目标文件名将与srcFile文件名相同. 采用默认的传输模式： OVERWRITE
     *
     * @param sftp
     * @param srcFile 本地文件的绝对路径
     * @param dest    目标文件的绝对路径
     */
    public void upload(ChannelSftp sftp, String srcFile, String dest) {
        try {
            File file = new File(srcFile);
            if (file.isDirectory()) {
                for (String fileName : file.list()) {
                    sftp.put(srcFile + File.separator + fileName, dest);
                }
            } else {
                sftp.put(srcFile, dest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * upload all the files to the server<br/>
     * 将fileList中的本地文件上传到目标服务器, 目标目录路径为 destPath,<br/>
     * destPath必须是目录，目标文件名将与源文件名相同. 采用默认的传输模式： OVERWRITE
     *
     * @param sftp
     * @param fileList 要上传到目标服务器的文件的绝对路径
     * @param destPath 目标文件的绝对路径, 一定是目录, 如果目录不存在则自动创建
     * @throws SftpException
     */
    public void upload(ChannelSftp sftp, List<String> fileList, String destPath) throws SftpException {
        try {
            sftp.cd(destPath);
        } catch (Exception e) {
            sftp.mkdir(destPath);
        }
        for (String srcFile : fileList) {
            upload(sftp, srcFile, destPath);
        }
    }

    /**
     * 使用sftp下载文件
     *
     * @param sftp
     * @param srcPath  sftp服务器上源文件的路径, 必须是目录
     * @param saveFile 下载后文件的存储路径, 若为目录, 则文件名将与目标服务器上的文件名相同
     * @param srcfile  目标服务器上的文件, 不能为目录
     */
    public void download(ChannelSftp sftp, String srcPath, String saveFile, String srcfile) {
        try {
            sftp.cd(srcPath);
            File file = new File(saveFile);
            if (file.isDirectory()) {
                sftp.get(srcfile, new FileOutputStream(file + File.separator + srcfile));
            } else {
                sftp.get(srcfile, new FileOutputStream(file));
            }
        } catch (Exception e) {
            logger.warn("download file: {} fail", srcPath + File.separator + srcfile, e);
        }
    }

    /**
     * 使用sftp下载目标服务器上某个目录下指定类型的文件, 得到的文件名与 sftp服务器上的相同
     *
     * @param sftp
     * @param srcPath   sftp服务器上源目录的路径, 必须是目录
     * @param savePath  下载后文件存储的目录路径, 一定是目录, 如果不存在则自动创建
     * @param fileTypes 指定类型的文件, 文件的后缀名组成的字符串数组
     * @return 下载成功文件个数
     */
    public int download(ChannelSftp sftp, String srcPath, String savePath, String... fileTypes) {
        List<String> fileList = new ArrayList<>();
        try {
            sftp.cd(srcPath);
            createDir(savePath);
            if (fileTypes.length == 0) {
                // 列出服务器目录下所有的文件列表
                fileList = listFiles(sftp, srcPath, "*");
                downloadFileList(sftp, srcPath, savePath, fileList);
            } else {
                for (String type : fileTypes) {
                    fileList = listFiles(sftp, srcPath, "*" + type);
                    downloadFileList(sftp, srcPath, savePath, fileList);
                }
            }
        } catch (Exception e) {
            logger.warn("download all file in path = '" + srcPath + "' and type in " + Arrays.asList(fileTypes)
                    + " fail", e);
        }

        return fileList.size();

    }

    private File createDir(String savePath) throws Exception {
        File localPath = new File(savePath);
        if (!localPath.exists() && !localPath.isFile()) {
            if (!localPath.mkdir()) {
                throw new Exception(localPath + " directory can not create.");
            }
        }
        return localPath;
    }

    /**
     * sftp下载目标服务器上srcPath目录下所有指定的文件.<br/>
     * 若本地存储路径下存在与下载重名的文件,仍继续下载并覆盖该文件.<br/>
     *
     * @param sftp
     * @param savePath 文件下载到本地存储的路径,必须是目录
     * @param fileList 指定的要下载的文件名列表
     * @throws SftpException
     */
    public void downloadFileList(ChannelSftp sftp, String srcPath, String savePath, List<String> fileList)
            throws SftpException {
        sftp.cd(srcPath);
        for (String srcFile : fileList) {
            logger.info("srcFile: " + srcFile);
            String localPath = savePath + File.separator + srcFile;
            sftp.get(srcFile, localPath);
        }
    }


    /**
     * 获取srcPath路径下以regex格式指定的文件列表
     *
     * @param sftp
     * @param srcPath sftp服务器上的目录
     * @param regex   需要匹配的文件名
     * @return
     * @throws SftpException
     */
    @SuppressWarnings("unchecked")
    public List<String> listFiles(ChannelSftp sftp, String srcPath, String regex) throws SftpException {
        List<String> fileList = new ArrayList<>();
        sftp.cd(srcPath); // 如果srcPath不是目录则会抛出异常
        if ("".equals(regex) || regex == null) {
            regex = "*";
        }
        Vector<ChannelSftp.LsEntry> sftpFile = sftp.ls(regex);
        String fileName;
        for (ChannelSftp.LsEntry lsEntry : sftpFile) {
            fileName = lsEntry.getFilename();
            fileList.add(fileName);
        }
        return fileList;
    }

    /**
     * 删除文件
     *
     * @param dirPath 要删除文件所在目录
     * @param file    要删除的文件
     * @param sftp
     * @throws SftpException
     */
    public void delete(String dirPath, String file, ChannelSftp sftp) throws SftpException {
        String now = sftp.pwd();
        sftp.cd(dirPath);
        sftp.rm(file);
        sftp.cd(now);
    }

    /**
     * Disconnect with server
     */
    public void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                    logger.info("sftp is closed already");
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            // Ignore
        }

    }

    public Session createSession(JSch jsch, String host, String username, int port) throws Exception {
        Session session;
        // 连接服务器，采用默认端口
        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else { // 采用指定的端口连接服务器
            session = jsch.getSession(username, host, port);
        }
        // 如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception(host + "session is null");
        }
        if (enableProxy) {
            session.setProxy(new ProxyHTTP(proxyHost, proxyPort));
        }
        // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        return session;
    }

}
