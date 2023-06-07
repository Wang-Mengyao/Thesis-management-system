<template>
    <el-container style="height: 100%;">
        <el-header style="height: 40%;">
            <el-container class="title"></el-container>
            <div class="search-container">
                <el-input v-model="searchInput" placeholder="请输入搜索关键字" class="search-input">
                    <el-select v-model="selectType" slot="prepend" placeholder="请选择" class="select-type">
                        <el-option label="题目" value="1"></el-option>
                        <el-option label="年份" value="2"></el-option>
                        <el-option label="作者" value="3"></el-option>
                        <el-option label="出版商" value="4"></el-option>
                        <el-option label="来源" value="5"></el-option>
                    </el-select>
                    <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                </el-input>
            </div>
            <el-container class="desc-container">
                {{this.libraryDesc}}
            </el-container>
        </el-header>
        <el-main style="height: 60%;" class="library-container">
            <el-table :data="literatureList" border>
                <el-table-column label="题目" prop="title"></el-table-column>
                <el-table-column label="年份" prop="year" width="60px"></el-table-column>
                <el-table-column label="作者" prop="author" width="150px"></el-table-column>
                <el-table-column label="出版商" prop="publisher"></el-table-column>
                <el-table-column label="来源" prop="source"></el-table-column>
                <el-table-column label="PDF" prop="pdf" width="60px">
                    <template slot-scope="scope">
                        <a v-if="scope.row.pdf !== ''" :href="scope.row.pdf">{{ 'PDF' }}</a>
                        <span v-else>{{ scope.row.pdf }}</span>
                    </template>
                </el-table-column>
            </el-table>

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
            ],
            literatureData: [
            {
                    id: "12312",
                    title: "Lorem Ipsum 1",
                    year: 2020,
                    author: "John Doe",
                    publisher: "Publisher A",
                    source: "Source A",
                    pdf: "www.baidu.com"
                },
                {
                    id: "12313",
                    title: "Lorem Ipsum 2",
                    year: 2019,
                    author: "Jane Smith",
                    publisher: "Publisher B",
                    source: "Source B",
                    pdf: ""
                },
                {
                    id: "12314",
                    title: "Lorem Ipsum 3",
                    year: 2021,
                    author: "Michael Johnson",
                    publisher: "Publisher C",
                    source: "Source C",
                    pdf: ""
                },
                {
                    id: "12315",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12316",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12317",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12318",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12319",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12320",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12321",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12322",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12323",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12324",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12325",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12326",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12327",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
                {
                    id: "12328",
                    title: "Lorem Ipsum 4",
                    year: 2022,
                    author: "Emily Davis",
                    publisher: "Publisher D",
                    source: "Source D",
                    pdf: ""
                },
            ], 
            libraryDesc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。123123123123123",
            currentPage: 1,
            pageSize: 15, 
            totalItems: 17, 
            literatureList : [],
            nowLibrary: ''
        }
    },
    methods: {
        search() {
            // 搜索
            console.log('Search:', this.searchInput, 'Select:', this.selectType);
            const searchTerm = this.searchInput.toLowerCase();
            this.fetchLibrary();

            let results = [];
            switch (this.selectType) {
                case '1': // 标题搜索
                    results = this.literatureData.filter(item => item.title.toLowerCase().includes(searchTerm));
                    break;
                case '2': // 年份搜索
                    results = this.literatureData.filter(item => item.year.toString().includes(searchTerm));
                    break;
                case '3': // 作者搜索
                    results = this.literatureData.filter(item => item.author.toLowerCase().includes(searchTerm));
                    break;
                case '4': // 出版商搜索
                    results = this.literatureData.filter(item => item.publisher.toLowerCase().includes(searchTerm));
                    break;
                case '5': // 来源搜索
                    results = this.literatureData.filter(item => item.source.toLowerCase().includes(searchTerm));
                    break;
                default:
                    break;
            }
            console.log(results);
            this.literatureData = results;
            this.fetchLiteratureData();
        },

        handleSizeChange(val) {
            this.pageSize = val;
            this.fetchLiteratureData();
        },

        handleCurrentChange(val) {
            this.currentPage = val;
            this.fetchLiteratureData();
        },

        fetchLiteratureData() {
            this.totalItems = this.literatureData.length;

            // 根据当前页数和每页显示的数量计算起始索引
            const startIndex = (this.currentPage - 1) * this.pageSize;
            // 从文献数据源中截取当前页的文献数据
            this.literatureList = this.literatureData.slice(startIndex, startIndex + this.pageSize);
        },

        fetchLibrary() {
            if(this.nowLibrary !== this.currentLib){
                console.log(this.currentLib);
                this.currentPage = 1;
                // 发起请求获取文献数据，传入this.currentLib，根据传出赋值literatureData和libraryDesc
                // TODO


                this.fetchLiteratureData();
            }
            console.log("library no change.");
        }
    },
    mounted() {
        // 初始化获取文献数据
        this.fetchLibrary();
    },
    props: {
        currentLib: {
            type: String,
            required: true
        }
    },
    watch: {
        currentLib: {
            immediate: true,
            handler() {
                this.fetchLibrary();
            }
        }
    }
}
</script>
  
<style>
    .el-header {
        background-color: #edb0bc;
    }
    .library-container {
        background-color: #edb0bc;
    }
    .title {
        background-color: #edb0bc;
        padding: 0;
        background-image: linear-gradient(to left, rgba(237, 176, 188, 1.0), transparent 40%, transparent 70%, rgba(237, 176, 188, 1.0)), url('./title.png');
        background-size: 70%;
        background-position: 50% 62%;
        background-repeat: no-repeat;
        height: 25%;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .search-container {
        background-color: #edb0bc;
        height: 30%;
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .search-input {
        width: 570px; /* 设置搜索框宽度 */
        position: absolute;
        top: 20px;
    }

    .search-dropdown {
        width: 570px; /* 设置下拉框宽度 */
        margin-top: 10px; /* 设置下拉框与搜索框的间距 */
    }
    .select-type {
        width: 14vb;
    }
    .desc-container {
        height: 40%;
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: center;
    }
</style>