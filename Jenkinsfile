//git 凭证id
def git_auth = "96b91bf1-3f92-4e18-94a6-d473504ac188"
//git的url地址
def git_url = "git@github.com:dinghaoxiaoqin/onlinemanage.git"
//镜像的版本号
def tag = "latest"

//阿里云镜像仓库地址registry.cn-hangzhou.aliyuncs.com/onlinemanage/online
def aliyun_url = "registry.cn-hangzhou.aliyuncs.com/onlinemanage/online"

//阿里云镜像库名称
def aliyun_project = "online"

//在jenkins凭据管理添加阿里云仓库的凭据id
def aliyun_auth = "e4b21adc-9795-4cdb-a14c-c765a3a00a98"

//构建的微服务名称
def boot_name = ""
node {

  stage('删除老的镜像'){
     sh "/var/opt/oldDeploy.sh"
     echo "删除镜像成功"
   }

  stage('拉取代码'){
     checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
  }

stage('编译 安装 公共模块'){
   sh "mvn -f online-common clean install"
  }

 stage('编译 安装 子模块'){

   echo "编译 系统模块"
   sh "mvn -f online-system clean install"

   echo "编译 核心模块"
   sh "mvn -f online-core clean install"

   echo "编译 generate模块"
   sh "mvn -f online-generate clean install"

   echo "编译 业务dis模块"
   sh "mvn -f online-dis clean install"
  }

  stage('编译 打包'){

     echo "编译 打包"
     sh "mvn -f online-security clean package dockerfile:build"
  }


  stage('上传镜像'){
     def name = "online-security"
     def imageName = ""
     echo "online-security:${tag}"
     imageName = name+":${tag}"
     boot_name = name

     //定义镜像的名字
     sh "docker tag ${imageName} ${aliyun_url}/${aliyun_project}/${imageName}"
//      //推送镜像到阿里云
//      withCredentials([usernamePassword(credentialsId: "${aliyun_auth}", passwordVariable: 'password', usernameVariable: 'username')]) {
//       //登录阿里云
//      sh "docker login -u ${username} -p ${password} registry.cn-hangzhou.aliyuncs.com"
//       //镜像上传到阿里云仓库
//      sh "docker push ${aliyun_url}/${aliyun_project}/${imageName}"

//      echo "镜像上传成功"
//         }
     //服务部署
     sh "/var/opt/deploy.sh"
    }

  }

