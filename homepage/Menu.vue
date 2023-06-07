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
                    <el-form-item label="公开">
                        <el-switch v-model="newLibrary.isPublic"></el-switch>
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
        <el-collapse-item v-for="library in libraries" :key="library.num" @click.native="setCurrentId(library.id)">
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
                    <el-form-item label="公开">
                        <el-switch v-model="library.updatedIsPublic"></el-switch>
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
            num: "1",
            id: "12345",
            topic: "人机交互",
            desc: "人机交互领域，二者协作完成某一目的。随着目的概念越发难以定义，这一定义逐渐泛化。",
            isPublic: false,
            showDesc: false,
            isEditing: false,
            updatedTopic: "",
            updatedDesc: "",
            updatedIsPublic: false
          },
          {
            num: "2",
            id: "12346",
            topic: "智慧驾驶",
            desc: "AI辅助驾驶，人与AI协作驾驶。",
            isPublic: true,
            showDesc: false,
            isEditing: false,
            updatedTopic: "",
            updatedDesc: "",
            updatedIsPublic: false
          }
        ],
        showAddForm: false,
        currentId: "12345",
        newLibrary: {
            topic: "",
            desc: "",
            isPublic: false
        }
      };
    },
    methods: {
        iniLibraries() {
            //在这里初始化libraries
            // 传入用户id
            //返回参数只需要id（按创建文献库时间得到的id）、num（序数）、topic、desc和ispublic
            //其余参数仿照confirmAdd()中进行默认定义
            //TODO

        },
        setCurrentId(id) {
            this.currentId = id;
            this.$emit('library-selected', id);
        },

        startEdit(library) {
            library.isEditing = true;
            library.updatedTopic = library.topic;
            library.updatedDesc = library.desc;
            library.updatedIsPublic = library.isPublic;
        },
        saveEdit(library) {
            library.topic = library.updatedTopic;
            library.desc = library.updatedDesc;
            library.isPublic = library.updatedIsPublic;
            library.isEditing = false;
            // 这里对文献库进行修改，前端已经对应修改，仅需调整后端
            //传入参数为library.id（用于找到文献库）、library.topic、library.desc、library.isPublic（后三者将被修改）
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
            const index = this.libraries.findIndex(item => item.num === library.num);
            if (index !== -1) {
                // delete item
                this.libraries.splice(index, 1);
                // reset `num` of items after item index
                for (let i = index; i < this.libraries.length; i++) {
                    this.libraries[i].num--;
                    // 更新对应的num，传入参数为this.libraries[i].id、this.libraries[i].num
                    // TODO 暂时什么都别做，按实时地给

                }

                //这里更新了libraries，进行删除操作，前文已经将前端中的libraries对应更新，仅需要调整后端
                //传入参数为library.id，后端数据库根据此id对应删除条目即可
                //TODO

            }
        },
        cancelAdd() {
            this.showAddForm = false;
            this.resetNewLibrary();
        },
        confirmAdd() {
        if (this.newLibrary.topic && this.newLibrary.desc) {
            const newNum = String(this.libraries.length + 1);
            const newLibraryItem = {
                id: "",
                num: newNum,
                topic: this.newLibrary.topic,
                desc: this.newLibrary.desc,
                isPublic: this.newLibrary.isPublic,
                showDesc: false,
                isEditing: false,
                updatedTopic: "",
                updatedDesc: "",
                updatedIsPublic: false
            };
            
            //这里获得了新的文献库条目newLibraryItem，前端已经更新，仅需调整后端
            //传入参数为topic、desc和isPublic，传出参数为newId
            //TODO
                
            
            //在这里使用传出参数newId
            //newLibraryItem.id = newId;

            this.libraries.push(newLibraryItem);
            this.showAddForm = false;
            this.resetNewLibrary();
            console.log(newLibraryItem.num);
        }
        },
        resetNewLibrary() {
            this.newLibrary.topic = "";
            this.newLibrary.desc = "";
            this.newLibrary.isPublic = false;
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
        height: 250px;
        padding: 10px;
    }

    .form-footer {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 50px; 
    }
</style>
  