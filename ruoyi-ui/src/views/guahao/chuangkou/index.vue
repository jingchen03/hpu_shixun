<template>
  <div>
    <h1 class="blue-box">基本信息</h1>
    <el-form ref="elForm" :model="formDatas" :rules="rules" size="small" label-width="100px">
      <el-row gutter="15">
        <el-col :span="7">
          <el-form-item label="病历号" prop="case_number">
            <el-input v-model="formDatas.case_number" clearable :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="姓名" prop="real_name">
            <el-input v-model="formDatas.real_name" clearable :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别" prop="gender">
            <el-select v-model="formDatas.gender" placeholder="选择性别" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in genderOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="15">
        <el-col :span="6">
          <el-form-item label="年龄" prop="age">
            <el-input v-model="formDatas.age" clearable :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="出生日期" prop="birthdate">
            <el-date-picker v-model="formDatas.birthdate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                            :style="{width: '100%'}" clearable></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="身份证号" prop="card_number">
            <el-input v-model="formDatas.card_number" clearable :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="15">
        <el-col :span="18">
          <el-form-item label="家庭地址" prop="home_address">
            <el-input v-model="formDatas.home_address" placeholder="请输入家庭地址" clearable
                      :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <h1 class="blue-box">挂号信息</h1>
      <el-row gutter="15">
        <el-col :span="7">
          <el-form-item label="结算类别" prop="settle_category_id">
            <el-select v-model="formDatas.settle_category_id" placeholder="请选择结算类别" clearable
                       :style="{width: '100%'}">
              <el-option v-for="(item, index) in settle_category_idOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="看诊日期" prop="visit_date">
            <el-date-picker v-model="formDatas.visit_date" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                            :style="{width: '100%'}" clearable></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="午别" prop="noon">
            <el-select v-model="formDatas.noon" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in noonOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="19">
        <el-col :span="7">
          <el-form-item label="挂号科室" prop="deptment_id">
            <el-select v-model="formDatas.deptment_id" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in deptment_idOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="号别" prop="regist_level_id">
            <el-select v-model="formDatas.regist_level_id" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in regist_level_idOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="看诊医生" prop="employee_id">
            <el-select v-model="formDatas.employee_id" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in employee_idOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row gutter="15">
        <el-col :span="7">
          <el-form-item label="病历本" prop="is_book" required>
            <el-switch v-model="formDatas.is_book" :active-value='"是"' :inactive-value='"否"'></el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="应收金额" prop="regist_money">
            <el-input v-model="formDatas.regist_money" placeholder="请输入应收金额" clearable
                      :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="收费方式" prop="regist_method">
            <el-select v-model="formDatas.regist_method" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in regist_methodOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
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
  components: {},
  props: [],
  data() {
    return {
      formDatas: {
        case_number: undefined,
        real_name: undefined,
        gender: '',
        age: undefined,
        birthdate: null,
        card_number: '',
        home_address: undefined,
        settle_category_id: 1,
        visit_date: null,
        noon: undefined,
        deptment_id: undefined,
        regist_level_id: undefined,
        employee_id: undefined,
        is_book: '是',
        regist_money: '',
        regist_method: undefined,
      },
      rules: {
        case_number: [{
          required: true,
          message: '',
          trigger: 'blur'
        }],
        real_name: [{
          required: true,
          message: '',
          trigger: 'blur'
        }],
        gender: [{
          required: true,
          message: '选择性别',
          trigger: 'change'
        }],
        age: [{
          required: true,
          message: '',
          trigger: 'blur'
        }],
        birthdate: [{
          required: true,
          message: '',
          trigger: 'change'
        }],
        card_number: [{
          required: true,
          message: '',
          trigger: 'blur'
        }],
        home_address: [{
          required: true,
          message: '请输入家庭地址',
          trigger: 'blur'
        }],
        settle_category_id: [{
          required: true,
          message: '请选择结算类别',
          trigger: 'change'
        }],
        visit_date: [{
          required: true,
          message: '',
          trigger: 'change'
        }],
        noon: [{
          required: true,
          message: '',
          trigger: 'change'
        }],
        deptment_id: [{
          required: true,
          message: '',
          trigger: 'change'
        }],
        regist_level_id: [{
          required: true,
          message: '',
          trigger: 'change'
        }],
        employee_id: [{
          required: true,
          message: '',
          trigger: 'change'
        }],
        regist_money: [{
          required: true,
          message: '请输入应收金额',
          trigger: 'blur'
        }],
        regist_method: [{
          required: true,
          message: '',
          trigger: 'change'
        }],
      },
      genderOptions: [{
        "label": "男",
        "value": "男"
      }, {
        "label": "女",
        "value": "女"
      }],
      settle_category_idOptions: [{
        "label": "1",
        "value": 1
      }, {
        "label": "2",
        "value": 2
      }, {
        "label": "3",
        "value": 3
      }, {
        "label": "4",
        "value": 4
      }, {
        "label": "5",
        "value": 5
      }],
      noonOptions: [{
        "label": "上午",
        "value": "上午"
      }, {
        "label": "下午",
        "value": "下午"
      }],
      deptment_idOptions: [{
        "label": "1",
        "value": 1
      }, {
        "label": "2",
        "value": 2
      }, {
        "label": "3",
        "value": 3
      }, {
        "label": "4",
        "value": 4
      }, {
        "label": "5",
        "value": 5
      }],
      regist_level_idOptions: [{
        "label": "1",
        "value": 1
      }, {
        "label": "2",
        "value": 2
      }, {
        "label": "3",
        "value": 3
      }, {
        "label": "4",
        "value": 4
      }, {
        "label": "5",
        "value": 5
      }],
      employee_idOptions: [{
        "label": "1",
        "value": 1
      }, {
        "label": "2",
        "value": 2
      }, {
        "label": "3",
        "value": 3
      }, {
        "label": "4",
        "value": 4
      }, {
        "label": "5",
        "value": 5
      }],
      regist_methodOptions: [{
        "label": "微信",
        "value": "微信"
      }, {
        "label": "支付宝",
        "value": "支付宝"
      }, {
        "label": "现金",
        "value": "现金"
      }],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
          // 打包参数
          // const params = this.formDatas
          // 发送post请求
          axios.post('http://localhost:8080/api/chuangkou',this.formDatas)
            .then(response => {
              // 处理响应结果
              console.log(response.data);
              this.$message({
                message: '恭喜你,提交成功！',
                type: 'success'
              });
            })
            .catch(error => {
              // 处理请求异常
              console.log(error);
            });
        }
      });
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}
</script>
<style>
.blue-box {
  display: inline-block;
  background-color: #409EFF;
  color: white;
  padding: 8px;
}
</style>






