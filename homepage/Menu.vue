<template>
    <div style="height: 100%;">
        <div class="add-container">
            <div class="add-button-container">
                <el-button class="add-button" size="mini" icon="el-icon-plus" @click="showAddForm = !(showAddForm)"></el-button>
            </div>
            <div v-if="showAddForm" class="add-form">
                <el-form :model="newLibrary" ref="addForm" label-width="40px">
                    <el-form-item label="名称">
                    <el-input v-model="newLibrary.topic"></el-input>
                    </el-form-item>
                    <el-form-item label="描述">
                    <el-input type="textarea" v-model="newLibrary.desc" resize="none"></el-input>
                    </el-form-item>
                </el-form>
                <div class="form-footer">
                    <el-button @click="cancelAdd">取消</el-button>
                    <el-button type="primary" @click="confirmAdd">确认</el-button>
                </div>
            </div>
        </div>
        <div>
        <el-collapse accordion>
        <el-collapse-item v-for="library in libraries" :key="library.id">
        <template slot="title">
          <span class="topic">{{ library.topic }}</span>
        </template>
            <div class="desc-container">
            <p>{{ library.desc }}</p>
            <div v-if="library.isEditing" class="edit-container">
                <el-form :model="library" label-width="40px">
                    <el-form-item label="名称">
                    <el-input v-model="library.updatedTopic"></el-input>
                    </el-form-item>
                    <el-form-item label="描述">
                    <el-input type="textarea" v-model="library.updatedDesc"></el-input>
                    </el-form-item>
                </el-form>
                <el-button type="primary" @click="saveEdit(library)">保存</el-button>
                <el-button @click="cancelEdit(library)">取消</el-button>
            </div>
            <div v-else>
                <el-button @click="startEdit(library)" class="edit-button" size="mini">
                <i class="el-icon-edit"></i>
                </el-button>

                <el-button @click="startDelete(library)" class="edit-button" size="mini">
                <i class="el-icon-delete"></i>
                </el-button>
            </div>

        </div>
        </el-collapse-item>
      </el-collapse>
    </div>
    </div>
  </template>
  
<script>
    export default {
    data() {
      return {
        libraries: [
          {
            id: "1",
            topic: "人机交互",
            desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
            is_public: true,
            showDesc: false,
            isEditing: false,
            updatedTopic: "",
            updatedDesc: ""
          },
          {
            id: "2",
            topic: "智慧驾驶",
            desc: "AI辅助驾驶，人与AI协作驾驶。",
            is_public: true,
            showDesc: false,
            isEditing: false,
            updatedTopic: "",
            updatedDesc: ""
          }
        ],
        showAddForm: false,
        newLibrary: {
            topic: "",
            desc: ""
        }
      };
    },
    methods: {

        iniLibraries() {
            //在这里初始化libraries
            //返回参数只需要id、topic和desc，其余参数仿照confirmAdd()中进行定义
            //TODO

        },

        startEdit(library) {
            library.isEditing = true;
            library.updatedTopic = library.topic;
            library.updatedDesc = library.desc;
        },
        saveEdit(library) {
            library.topic = library.updatedTopic;
            library.desc = library.updatedDesc;
            library.isEditing = false;
            // 这里对文献库进行修改，前端已经对应修改，仅需调整后端
            //传入参数为library.id、library.topic、library.desc
            //TODO
            
        },
        cancelEdit(library) {
            library.isEditing = false;
        },
        startDelete(library) {
            this.$confirm('确定要删除此文献库吗？', '提示', {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
            this.deleteItem(library);
            }).catch(() => {
                // cancel deleting
            });
        },
        deleteItem(library) {
            const index = this.libraries.findIndex(item => item.id === library.id);
            if (index !== -1) {
                this.libraries.splice(index, 1);

                //这里更新了libraries，进行删除操作，前文已经将前端中的libraries对应更新，仅需要调整后端
                //参数为library.id，后端数据库根据此id对应删除条目即可
                //TODO

            }
        },
        cancelAdd() {
            this.showAddForm = false;
            this.resetNewLibrary();
        },
        confirmAdd() {
        if (this.newLibrary.topic && this.newLibrary.desc) {
            const newId = String(this.libraries.length + 1);
            const newLibraryItem = {
            id: newId,
            topic: this.newLibrary.topic,
            desc: this.newLibrary.desc,
            is_public: true,
            showDesc: false,
            isEditing: false,
            //isDeleting: false,
            updatedTopic: "",
            updatedDesc: ""
            };
            this.libraries.push(newLibraryItem);
            //这里获得了新的文献库条目newLibraryItem，前端已经更新，仅需调整后端
            //参数为id，topic和desc，按照newLibraryItem.id的方式访问
            //TODO

            this.showAddForm = false;
            this.resetNewLibrary();
        }
        },
        resetNewLibrary() {
            this.newLibrary.topic = "";
            this.newLibrary.desc = "";
        }
        }
    };
</script>
  
<style scoped>
    .desc-container {
    margin-top: 10px;
    text-align: left; /* 靠左展示 */
    padding-left: 5%; /* 左侧内边距 */
    }

    .topic {
    font-size: 16px; /* 调整字体大小 */
    color: #333; /* 调整字体颜色 */
    padding-left: 5%;
    }
    
    .edit-container {
        margin-top: 10px;
        text-align: center;
        padding-right: 5%;
    }
    
    .edit-button {
        margin-top: 10px;
        font-size: 12px; /* 调整按钮大小 */
        padding: 4px 8px; /* 调整按钮内边距 */
    }
    .add-container {
        padding: 10px;
    }
    .add-button-container {
        height: 50px;
        text-align: right;
        position: relative;
    }
    .add-button {
        font-size: 12px; /* 调整按钮大小 */
        position: absolute;
        top: 5px;
        right: 0;
    }
    .add-form {
        background-color: #ffffff;  /*设置背景颜色 */
        height: 200px;
        padding: 10px;
    }

    .form-footer {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 50px; 
    }
</style>
  