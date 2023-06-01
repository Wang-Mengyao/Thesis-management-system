<template>
    <el-container style="height: 100%;">
        <el-header style="height: 30%;">
            <div class="title"></div>
            <div class="search-container">
                <el-input v-model="searchInput" placeholder="请输入搜索关键字" class="search-input">
                    <el-select v-model="selectType" slot="prepend" placeholder="请选择" class="select-type">
                        <el-option label="文献库" value="1"></el-option>
                        <el-option label="文献" value="2"></el-option>
                    </el-select>
                    <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                </el-input>
            </div>
        </el-header>
        <el-main style="height: 70%;" class="library-container">
            main
        </el-main>
    </el-container>
</template>
  
<script>

export default {
    data() {
        return {
            searchInput: '',
            showDropdown: false,
            selectType: '',
            selectedOption: '',
            searchOptions: [
                // 搜索结果选项
                { id: 1, label: '选项1', value: 'option1' },
                { id: 2, label: '选项2', value: 'option2' },
                { id: 3, label: '选项3', value: 'option3' }
            ]
        }
    },
    methods: {
        search() {
        // 处理搜索逻辑
        console.log('Search:', this.searchInput, 'Select:', this.selectType);
        }
    }
}
</script>
  
<style>
    .el-header {
        background-color: #edb0bc;
    }
    .library-container {
        background-color: #9d85d6;
    }
    .title {
        background-color: #edb0bc;
        padding: 0;
        background-image: linear-gradient(to left, rgba(237, 176, 188, 1.0), transparent 40%, transparent 70%, rgba(237, 176, 188, 1.0)), url('./title.png');
        background-size: 70%;
        background-position: 50% 62%;
        background-repeat: no-repeat;
        height: 34%;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .search-container {
        background-color: #edb0bc;
        height: 66%;
    }

    .search-input {
        width: 570px; /* 设置搜索框宽度 */
        
    }

    .search-dropdown {
    width: 570px; /* 设置下拉框宽度 */
    margin-top: 10px; /* 设置下拉框与搜索框的间距 */
    }
    .select-type {
        width: 14vb;
    }
</style>