<template>
    <el-container style="height: 100vh;">
        <el-container style="height: 100%;">
            <el-aside style="width : 300px; display: flex; flex-direction: column; height: 100%;">
                <el-header style="height: 10%;" class="aside-header">文献库</el-header>
                <Menu style="height: 90%;"></Menu>
            </el-aside>
            <el-main style="height: 100%;" class="main-content">
                    <MainContent></MainContent>
            </el-main>
        </el-container>
    </el-container>
</template>
  
<script>
import Menu from './Menu.vue'
import MainContent from './MainContent.vue'

export default {
    components: {
        Menu,
        MainContent
    }
}
</script>
  
<style>
    .el-aside {
        background-color: #51946e;
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