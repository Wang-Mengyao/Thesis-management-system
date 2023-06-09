<template>
    <el-container style="height: 100vh;">
        <el-header class="title">
            RoseSCI
        </el-header>
        <el-container class="search-area-for-conmmunity" style="height: 40%;">
            <div class="background-container">
                <img src="./title.png" alt="your-image-alt" class="background-image" />
            </div>
            <div class="search-container-for-conmmunity">
                <el-input v-model="searchInput" placeholder="搜索感兴趣的文献库" class="search-input">
                <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                </el-input>
            </div>
        </el-container>
        <el-main class="main-content">
            <el-aside class="right-aside">
                <div class="buttons">
                    <template v-if="!currentUser">
                        <el-container class="intro-text">
                            <span>一键注册，即免费获得 200 篇文献上传空间</span>
                        </el-container>
                        <el-button class="button" @click="redirectToLogin">登录 / 注册</el-button>
                    </template>
                    <template v-else>
                        <el-container class="intro-text">
                            <span>在个人主界面管理文献库和文献</span>
                        </el-container>
                        <el-button class="button" @click="redirectToMain">主界面</el-button>
                    </template>
                </div>
            </el-aside>
            <el-container class="library-cards">
                <el-header>文献库</el-header>
                <el-row class="library-row">
                    <el-col :span="7" v-for="library in libraries" :key="library.id">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                        <span>{{ library.topic }}</span>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="redirectToGuestpage(library.id)">查看</el-button>
                        </div>
                        <div class="card-content">
                            <p>{{ library.desc | truncateText }}</p>
                        </div>
                        <div class="card-user">
                            <p>{{ library.userName }}</p>
                        </div>
                        
                    </el-card>
                    </el-col>
                </el-row>
                <div class="pagination">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[15, 30, 50]"
                    :page-size="pageSize"
                    :total="totalItems"
                    layout="prev, pager, next, jumper"
                ></el-pagination>
                </div>
            </el-container>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        data() {
            return {
                searchInput: '',
                currentUser: null,
                libraries: [
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "hiphop",
                        desc: "中国有嘻哈",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "watermelon",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。中国有西瓜。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "膝盖和西瓜",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。人机交互不仅涉及对人类操作的心理建模，也需要包含对上下文环境的态势感知，在二者交互之中，以人为中心，建立双向的信任。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                    {
                        id: '12344',
                        topic: "人机交互",
                        desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
                        userName: "Amy"
                    },
                ],
                searchResultes: [],
                currentPage: 1,
                pageSize: 15,
                totalItems: 3001,
            }
        },
        methods: {
            redirectToLogin() {
                window.location.href = "/login"; // 需要调整路由 TODO
            },
            redirectToMain() {
                window.location.href = "/homepage"; // 需要调整路由 TODO
            },
            redirectToGuestpage(id) {
                console.log(id);
                // 根据文献库的id重定向到相应文献库的访客界面
                // TODO

            },
            iniUser() {
                // 在这里初始化用户名
                // 如果还没有登录，那么将this.currentUser置为null即可
                // TODO

                this.currentUser = null;
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.fetchlibraries();
            },
            handleCurrentChange(val) {
                console.log(this.currentPage);
                this.currentPage = val;
                console.log(this.currentPage);
                this.fetchlibraries();
            },
            fetchlibraries() {
                // 在这里取到文献库们，传入参数为this.currentPage, this.pageSize
                // 传出参数需要初始化libraries
                // TODO

            },
            iniTotal() {
                // 初始化总的文献库的数量，比如总共有3001个文献库，这是为了页码的展示
                // TODO
                this.totalItems = 3001;
            },
            search() {
                if(this.searchInput !== ''){
                    console.log(this.searchInput);
                    const searchTerm = this.searchInput.toLowerCase(); // 大小写模糊处理
                    this.currentPage = 1;

                    // 可以按批取文献库（如一次取300个），然后在前端查找，使用this.searchResultes暂存批
                    // this.libraries = this.searchResultes.filter(item => item.title.toLowerCase().includes(searchTerm));
                    // 问题在于，按批次取，不一定能保证这一批里能凑到pageSize个文献库，一页占不满

                    // 推荐，后端直接根据searchTerm、this.currentPage、this.pageSize返回结果赋值this.libraries
                    // 不采用 一次性返回所有检索结果 需要多次检索每次只返回其中对应一页 的原因：翻页逻辑需要在检索场景下重新调整，需要增加逻辑区分检索场景
                }else{
                    // 搜索框为空，重新回到原始推荐页面
                    console.log('back to original page');
                    this.currentPage = 1;
                    this.fetchlibraries();
                }
            },
        },
        mounted() {
            this.iniUser();
            this.currentPage = 1;
            this.iniTotal();
            this.fetchlibraries();
        },
        filters: {
            truncateText(value) {
                if (value.length > 140) {
                    return value.slice(0, 140) + "……";
                }
                return value;
            }
        },
    }

</script>

<style>
    .pagination {
        text-align: center;
    }
    .card-user {
        text-align: right;
        margin: 10px;
        font-size: small;
        color: rgb(124, 124, 124);
    }
    .library-row {
        width: 1200px;
        margin: 30px;
    }
    .box-card {
        margin: 10px;
        margin-bottom: 30px;
        height: 300px;
    }
    .library-cards {
        background-color: rgb(255, 255, 255);
        padding: 30px;
    }
    .buttons {
        background-color: #ffccda;
        height: 240px;
        margin-top: 30px;
        margin-right: 40px;
        position: absolute;
    }
    .intro-text {
        padding: 60px;
        padding-bottom: 40px;
        font-size: medium;
    }
    .button {
        position: relative;
        left: 65px;
        width: 130px;
        background-color: #f2f2f2;
    }
    .title {
        height: 50px;
        background-color: #e19bb2;
        color: azure;
        font-size: xx-large;
        padding: 10px;
        z-index: 2;
    }
    .search-area-for-conmmunity {
        background-color: antiquewhite;

    }
    .main-content {
        background-color: rgb(234, 232, 241);
        position: relative;
    }
    .right-aside {
        position: absolute;
        top: 8px;
        right: 0;
        height: 100%;
        width: 200px;
    }
    .search-area {
        position: relative;
    }

    .background-container {
        position: absolute;
        top: -188px;
        left: 0;
    }

    .background-image {
        width: 1536px;
        height: 550px;
    }

    .search-container-for-conmmunity {
        position: relative;
        z-index: 1;
        left: 550px;
        top: 20px
    }

    .search-input {
        width: 460px;
    }


</style>