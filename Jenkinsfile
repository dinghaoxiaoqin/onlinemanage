//git 凭证id
def git_auth = "96b91bf1-3f92-4e18-94a6-d473504ac188"
//git的url地址
def git_url = "git@github.com:dinghaoxiaoqin/onlinemanage.git"
//镜像的版本号
def tag = "latest"

//构建的微服务名称
def boot_name = ""
node {
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

  stage('服务部署'){
     echo "测试环境部署online-manage--------------"
     sh "/var/opt/deploy.sh"
  }

  }

}
