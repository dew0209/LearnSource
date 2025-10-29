package quickstart;


/**
 *
 * 试用云服务器：
 *  安全组：就是防火墙
 *  专用网络：搭建局域网，局域网直接可以通过私有ip访问
 *
 *  测试服务器：
 *      用Xshell8连接测试安装nginx
 *          yum install nginx
 *          systemctl start nginx
 *          设置安全组，添加80访问规则
 *          浏览器访问http://8.136.60.165:80/
 *          成功
 *          找到nginx的默认访问页面index.html 执行命令：ech0 666 > index.html
 *          重启nginx  systemctl restart nginx
 *          再次访问，页面出现666 成功。阿里云服务器完成创建实例
 *
 */
public class AliYunTest {

    public static void main(String[] args) {


    }

}
