<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="单行文本" prop="field102">
        <el-input v-model="formData.field102" placeholder="请输入单行文本" clearable :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      formData: {
        field102: '',
      },
      rules: {
        field102: [{ required: true, message: '必填', trigger: 'blur' }],
      },
    };
  },
  methods: {
    submitForm() {
      this.$refs.elForm.validate(valid => {
        if (valid) {
          // 打包参数
          const params = {
            field102: this.formData.field102,
          };
          // 发送post请求
          axios.post('http://localhost:8080/api/data', params)
            .then(response => {
              // 处理响应结果
              console.log(response.data);
            })
            .catch(error => {
              // 处理请求异常
              console.log(error);
            });
        }
      });
    },
    resetForm() {
      this.$refs.elForm.resetFields();
    },
  },
};
</script>

<style>
</style>
