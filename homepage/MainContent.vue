<template>
    <el-container style="height: 100%;">
        <el-header style="height: 20%;">
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
        </el-header>
        <el-main style="height: 80%;" class="library-container">
            <el-button type="text" size="mini" @click="openAddDialog" class="custom-button">添加条目</el-button>
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
                <el-table-column label="操作" width="100px">
                    <template slot-scope="scope">
                    <el-button type="text" size="small" @click="confirmDelete(scope.$index)">
                        删除
                    </el-button>
                    <el-button type="text" size="small" @click="editItem(scope.row, scope.row.id)">
                        编辑
                    </el-button>
                    <el-button type="text" size="small" @click="openUploadDialog(scope.row)">
                        上传PDF
                    </el-button>
                    </template>
                </el-table-column>
            </el-table>

            <el-dialog title="添加条目" :visible.sync="addDialogVisible">
            <el-form :model="newItem" label-width="80px">
                <el-form-item label="题目">
                <el-input v-model="newItem.title"></el-input>
                </el-form-item>
                <el-form-item label="年份">
                <el-input v-model="newItem.year"></el-input>
                </el-form-item>
                <el-form-item label="作者">
                <el-input v-model="newItem.author"></el-input>
                </el-form-item>
                <el-form-item label="出版商">
                <el-input v-model="newItem.publisher"></el-input>
                </el-form-item>
                <el-form-item label="来源">
                <el-input v-model="newItem.source"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addItem">确定</el-button>
            </div>
            </el-dialog>

            <el-dialog title="编辑条目" :visible.sync="editDialogVisible">
            <el-form :model="editItemData" label-width="80px">
                <el-form-item label="题目">
                <el-input v-model="editItemData.title"></el-input>
                </el-form-item>
                <el-form-item label="年份">
                <el-input v-model="editItemData.year"></el-input>
                </el-form-item>
                <el-form-item label="作者">
                <el-input v-model="editItemData.author"></el-input>
                </el-form-item>
                <el-form-item label="出版商">
                <el-input v-model="editItemData.publisher"></el-input>
                </el-form-item>
                <el-form-item label="来源">
                <el-input v-model="editItemData.source"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="saveEditItem">保存</el-button>
            </div>
            </el-dialog>

            <el-dialog title="上传PDF" style="height: 650px;" :visible="uploadDialogVisible" @close="closeUploadDialog">
            <el-upload
                class="upload-demo"
                drag
                action="/your-upload-url"
                :on-success="handleUploadSuccess"
                :before-upload="beforeUpload"
            >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip">只能上传PDF文件</div>
            </el-upload>
            <div slot="footer" class="dialog-footer">
                <el-button @click="closeUploadDialog">取消</el-button>
                <el-button type="primary" @click="confirmUpload">确认</el-button>
            </div>
            </el-dialog>

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
            selectType: '',
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
            currentPage: 1,
            pageSize: 15, 
            totalItems: 17, 
            literatureList : [],
            nowLibrary: '',
            addDialogVisible: false,
            editDialogVisible: false,
            uploadDialogVisible: false,
            newItem: {
                id: '',
                title: '',
                year: '',
                author: '',
                publisher: '',
                source: '',
                pdf: ''
            },
            editItemData: {},
            deleteId: '',
            uploadPDF: '',
        }
    },
    methods: {
        confirmDelete(index) {
            const self = this;
            this.$confirm('确定要删除该条目吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            })
            .then(() => {
            self.deleteItem(index);
            })
            .catch(() => {

            });
        },

        deleteItem(i) {
            this.deleteId = this.literatureList[i].id;
            console.log(i)
            this.literatureData.splice((this.currentPage - 1) * this.pageSize + i, 1);
            this.currentPage = 1;
            this.fetchLiteratureData();      
            // 前端已经调整，仅需在数据库中删除对应id的文献，传入参数为this.deleteId
            // TODO

        },

        openAddDialog() {
            this.addDialogVisible = true;
        },
        addItem() {
            if(this.newItem.title != '' && this.newItem.year != '' && this.newItem.author != ''&& this.newItem.publisher != ''&& this.newItem.source != ''){
                // 已经检查所有项不为空，传入上述5个项保存该文献记录，传出参数newId
                // TODO
                
                const newId = '12345';// 此句需修改为对应实现



                // 以下代码正在修改前端，勿动
                this.newItem.id = newId;
                this.literatureData.push(this.newItem);
                this.fetchLiteratureData();
                this.addDialogVisible = false;
                this.newItem = {
                    id: '',
                    title: '',
                    year: '',
                    author: '',
                    publisher: '',
                    source: '',
                    pdf: ''
                };
            }else{
                this.$message.error('内容不能为空！');
            }
            this.addDialogVisible = false;
        },
        editItem(item, id) {
            this.editItemData = Object.assign({}, item);
            this.editItemData.id = id;
            //console.log('editItemData id:', this.editItemData.id);
            this.editDialogVisible = true;

        },
        saveEditItem() {
            if(this.editItemData.title != '' && this.editItemData.year != '' && this.editItemData.author != ''&& this.editItemData.publisher != ''&& this.editItemData.source != ''){
                const editedItemIndex = this.literatureData.findIndex(item => item.id === this.editItemData.id);
                if (editedItemIndex !== -1) {
                    this.literatureData.splice(editedItemIndex, 1, this.editItemData);
                }
                this.fetchLiteratureData();
                // 前端已完成更新，仅需更新后端，保存编辑
                // 传入参数为this.editItemData，其中包含id，可以据此找到对应条目
                // 通过this.editItemData.xxx的方式访问相应内容
                // TODO


            }else{
                this.$message.error('内容不能为空！');
            }
            this.editDialogVisible = false;
        },


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

        // 以下函数处理PDF上传，因为PDF无法暂存在浏览器，因此将直接与后端交互
        // 此处仅为部分代码实现，最终实现可能包含多个函数的完善
        // 此处使用了element UI 的 el-upload 组件，请了解该组件的使用和前文对该组件的调用
        // TODO

        // 打开上传窗口，并将this.uploadPDF置为当前文献的id，以便后续使用
        openUploadDialog(row) {
            this.uploadDialogVisible = true;
            this.uploadPDF = row.id;
            console.log(this.uploadPDF);
        },

        // 关闭上传窗口
        closeUploadDialog() {
            this.uploadDialogVisible = false; 
        },

        // 在上传成功后接收response
        handleUploadSuccess(response, file) {
            console.log('上传成功', response, file);
            // 可以在这里触发其他相关操作，如保存文件路径等

        },

        // 上传之前的验证，仅允许上传PDF文件，否则弹error窗
        beforeUpload(file) {
            const isPDF = file.type === 'application/pdf';
            if (!isPDF) {
                this.$message.error('只能上传PDF文件');
            }
            return isPDF;
        },

        // 确认上传
        confirmUpload() {
            // 执行上传操作，根据实际需求进行处理
            // 确认修改后端
            // 可能有一些操作


            // 还将修改前端显示，这里假设可以通过文献id访问该文献
            const pdfIndex = this.literatureData.findIndex(item => item.id === this.uploadPDF);
            if (pdfIndex !== -1) {
                this.literatureData[pdfIndex].pdf = this.uploadPDF; // 注意这里可能需要修改

                // 因为pdf元素应该是一个链接，可以直接打开为阅读界面
                // 还需要与苏进一步协商路由的设计
                this.fetchLiteratureData();
            }else{
                this.$message.error('无法上传！');
            }
            this.closeUploadDialog(); // 关闭上传对话框
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
                // 发起请求获取文献数据，传入this.currentLib，根据传出赋值literatureData
                // TODO


                this.fetchLiteratureData();
            }
            console.log("library no change.");
        },
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
        height: 42%;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .search-container {
        background-color: #edb0bc;
        height: 58%;
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

    .pagination {
        margin-top: 20px;
        display: flex;
        justify-content: center;
    }

    .upload-demo {
        border: 1px dashed #409eff;
        border-radius: 6px;
        padding: 20px 0;
        text-align: center;
        color: #999;
        height: 200px;
    }
    .upload-demo .el-icon-upload {
        font-size: 28px;
        color: #409eff;
    }
    .upload-demo .el-upload__text em {
        color: #666;
    }
    .upload-demo .el-upload__tip {
        margin-top: 10px;
        color: #666;
    }
    .custom-button {
        background-color: aliceblue;
        width: 80px;
        color: #666;
        height: 30px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 10px;
    }

</style>