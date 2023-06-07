<template>
    <el-container style="height: 100vh;">
        <el-container style="height: 100%;">
            <el-aside style="width : 400px; display: flex; flex-direction: column; height: 100%;">
                <el-header style="height: 10%;" class="aside-header">评论区</el-header>
                <Comment :currentLib="currentLibrary" style="height: 90%;"></Comment>
            </el-aside>
            <el-main style="height: 100%;" class="main-content">
                    <MainContentForComment :currentLib="currentLibrary"></MainContentForComment>
            </el-main>
        </el-container>
    </el-container>
</template>
  
<script>
import Comment from './Comment.vue'
import MainContentForComment from './MainContentForComment.vue'

export default {
    components: {
        Comment,
        MainContentForComment
    },
    data() {
        return {

        };
    },
    mounted() {
        
    },
    computed: {
        currentLibrary() {
            return this.iniLibrary();
        }
    },
    methods: {
        iniLibrary() {
            // 在这里初始化访客界面所访问的文献库id
            // TODO
            
            return "12347";// 后续需修改此句

        }
    }
}
</script>
  
<style>
    .el-aside {
        background-color: #fafafa;
        color: #333;
        text-align: center;
        line-height: 200px;
    }
    .main-content {
        background-color: #ffffff;
        color: #333;
        text-align: center;
        line-height: 200px;
        padding: 0;
    }
    .aside-header {
        background-color: #519461;
        color: #ffffff;
        font-size: larger;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        text-align: center;
        line-height: 70px;
    }

</style>