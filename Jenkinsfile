//git 凭证id
def git_auth = "96b91bf1-3f92-4e18-94a6-d473504ac188"
//git的url地址
def git_url = "git@github.com:dinghaoxiaoqin/onlinemanage.git"
//镜像的版本号
def tag = "latest"
//阿里云镜像仓库地址
def aliyun_url = "registry.cn-hangzhou.aliyuncs.com/dhqxq/dockerdepository"
//阿里云镜像库名称
def aliyun_project = "dockerdepository"
//阿里云仓库的凭据id
def aliyun_auth = "0c9d941e-c2f6-44b8-a8a5-abfc3a985340"
//构建的微服务名称
def boot_name = ""
node {
  stage('拉取代码'){
     checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${git_auth}", url: "${git_url}"]]])
  }

}
