<template>
    <div class="comment-area">
        <div class="comment-block">
            <el-form ref="commentForm" :model="newComment" label-width="80px">
            <el-form-item label="说点什么">
                <el-input type="textarea" v-model="newComment.text"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitComment">发布</el-button>
            </el-form-item>
            </el-form>
        </div>
        <div class="comment-list">
            <ul>
            <li v-for="(comment, index) in comments" :key="index" class="comment-item">
                <el-form :model="comment" class="comment-form" label-width="40px">
                <el-form-item>
                    <el-container class="comment-text">{{ comment.text }}</el-container>
                    <el-container class="comment-user">{{ comment.user }}</el-container>
                </el-form-item>
                </el-form>
            </li>
            </ul>
        </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        comments: [
            { user: "wmyyyyyyyyyyyyyyyyyyyyyy", text: "人脸恢复通常依赖于面部先验，如面部几何先验或参考先验，以恢复真实和忠实的细节。然而，非常低质量的输入不能提供精确的几何先验，而高质量的参考是不可访问的，限制了现实场景的适用性。在这项工作中，我们提出了GFP-GAN，它利用封装在预先训练的人脸GAN中的丰富多样的先验进行盲人脸恢复。这种生成面部先验(GFP)通过空间特征变换层被纳入人脸恢复过程中，这使得我们的方法能够实现真实性和保真度的良好平衡。" },
            { user: "User2", text: "这是第二条评论。" },
            { user: "User3", text: "这是第三条评论。" }
        ],
        newComment: {
          text: ""
        },
        currentUser: "Amy"
      };
    },
    methods: {
      submitComment() {
        if (this.currentUser !== "" && this.newComment.text.trim() !== "") {
          this.comments.push({
            user: this.currentUser,
            text: this.newComment.text
          });
          // 传入评论的this.currentUser、this.newComment.text、this.currentLib，从而为该文献库添加新的评论
          // TODO

          
          this.newComment.text = "";
        }
      },
      iniUser() {
        // 在这里初始化当前用户名
        // TODO
        this.currentUser = "wmy";// 需删改
      }
    },
    mounted() {
        this.iniUser();
    },
    props: {
        currentLib: {
            type: String,
            required: true
        }
    }
  };
  </script>
  
  <style scoped>
.comment-list {
  margin-top: 30px;
}

.comment-item {
  margin-bottom: 10px;
  margin: 5px;
}

.comment-block {
    background-color: #ffffff;
    text-align: left;
    padding: 10px;
    margin-top: 10px;
}
.comment-form {
    background-color: #eeeefc;
    position: relative;
    border-radius: 6px;
}

.comment-text {
    margin-top: 10px;
    margin-bottom: 5px;
    display: flex;
    flex-direction: column;
    position: relative;
    right: 20px;
    text-align: left;
    line-height: 1.5;
}

.comment-user {
    display: flex;
    flex-direction: column;
    font-size: 12px;
    color: #888;
    position: relative;
    left: 0px;
}
  </style>
  