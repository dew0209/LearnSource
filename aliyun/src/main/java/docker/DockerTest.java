package docker;


/**
 *
 * 安装docker
 * yum install -y yum-utils
 *  设置docker下载路径
 *      yum-config-manager --add-repo http://download.docker.com/linux/centos/docker-ce.repo
 *
 *  安装docker：
 *      yum install -y docker-ce docker-ce-cli containerd.io
 *   修改镜像加速：
 *      在/etc/docker目录下配置
 *          daemon.json文件：
 *              {
 *                  "registry-mirrors": ["https://5nj9ie23.mirror.aliyuncs.com"]
 *              }
 *   开机启动并现在自启
 *   systemctl enable  docker --now
 *
 *   docker pull nginx #下载的最新版本
 *   docker pull 镜像名:版本号
 *   docker pull redis:6.2.4
 *
 *   docker images #查看本地所有镜像
 *   docker rmi 镜像名:版本号 #删除本地镜像
 *
 *
 *    docker update 0d0 --restart=always #已经启动设置为开机自启
 *    docker ps #查看正在运行的容器
 *    docker ps -a #查看所有
 *    docker rm 容器id/名字
 *    docker stop 容器id/名字
 *   启动容器：
 *      docker run --name=mynginx -d --restart=always -p 88:80 nginx # 启动 -d后台启动 --restart=always开机自启 -p 88:80端口映射
 *   进入docker：
 *      docker exec -it 容器id /bin/bash
 *          niginx欢迎页位置：/usr/share/nginx/html
 *          echo 8888 > index.html
 *          刷新页面（禁用缓存哈），变为8888
 *
 *   docker和外部文件进行挂载：
 *
 *   打包镜像：
 *          docker commit -a "lvlu" -m "nginx 首页变化" 5dd55fc1bf21 lvlu_nginx:v1.0
 *          将正在运行的镜像停止 docker rmi mynginx
 *          重启：docker run --name=mynginx -d --restart=always -p 88:80 nginx
 *          访问nginx首页，发现8888没有了，变成最初始的了。
 *          启动新打包的镜像：docker run -d -p 88:80 lvlu_nginx:v1.0
 *          访问nginx首页，为8888.
 *        保存镜像为本地文件：
 *          docker save -o abc.tar lvlu_nginx:v1.0
 *          别的机器加载：
 *              docker load -i abc.tar
 *        推送镜像到远程仓库：
 *              修改镜像名字为仓库指定
 *              docker tag lvlu_nginx:v1.0 crpi-h1r3fcn1bs7i9yak.cn-hangzhou.personal.cr.aliyuncs.com/k8s_test_ldew/k8s-test:v1.0
 *              登录到docker
 *                  docker login 到阿里云
 *                  docker push crpi-h1r3fcn1bs7i9yak.cn-hangzhou.personal.cr.aliyuncs.com/k8s_test_ldew/k8s-test:v1.0
 *              删除本地镜像从阿里云拉取
 *                  docker rm crpi-h1r3fcn1bs7i9yak.cn-hangzhou.personal.cr.aliyuncs.com/k8s_test_ldew/k8s-test:v1.0
 *                  docker pull crpi-h1r3fcn1bs7i9yak.cn-hangzhou.personal.cr.aliyuncs.com/k8s_test_ldew/k8s-test:[镜像版本号]
 *                  可以用docker tag 重新命名下，然后启动，可以正常访问nginx
 *        日志：
 *          docker logs 容器id
 *
 *
 */
public class DockerTest {

    public static void main(String[] args) {

    }

}
