<template>
  <div>
    <img alt="Vue logo" src="../assets/logo.png" width="3%">
    <h1>Hotel List</h1>

    <el-table 
      :data="tableData"
      :cell-style="{ textAlign: 'center' }"
      :header-cell-style="{ 'text-align': 'center' }"
      stripe style="width: 80%;margin: 0 auto;"
    >
      <el-table-column prop="name" label="Hotel Name"></el-table-column>
      <el-table-column prop="city" label="City"></el-table-column>
      <el-table-column prop="district" label="District"></el-table-column>
      <el-table-column prop="date" label="Date"></el-table-column>
      <el-table-column prop="time" label="Check-in Time"></el-table-column>
      <el-table-column prop="price" label="Price"></el-table-column>
      <el-table-column prop="type" label="Room Type"></el-table-column>
      <el-table-column label="Operations" min-width="120">
      <template #default="scope">
        <el-button type="danger" size="small" @click="handleDelete(scope.$index)">
          Delete
        </el-button>
      </template>
    </el-table-column>
    </el-table>

    <el-button style="margin-left: 0%; margin-top: 20px" type="primary" @click="dialogVisible=true">Add New Hotel</el-button>

    <el-dialog
      v-model="dialogVisible"
      title="Add A New Hotel Item"
      width="40%"
      @close="resetForm()"
    >
      <el-form
        ref="hotelForm"
        :model="hotelForm"
        :rules="rules"
        label-width="auto"
        label-position="right"
        size="default"
      >  
        <el-form-item label="Hotel Name" prop="name">
          <el-input v-model="hotelForm.name" />
        </el-form-item>

        <el-form-item label="City" prop="city">
          <el-radio-group v-model="hotelForm.city">
            <el-radio value="GUANG ZHOU">GUANG ZHOU</el-radio>
            <el-radio value="SHEN ZHEN">SHEN ZHEN</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="District" prop="district">
          <el-select v-model="hotelForm.district" placeholder="please select district">
            <el-option v-for="district in filteredDistricts" :key="district" :label="district" :value="district" />
          </el-select>
        </el-form-item>

        <el-form-item label="Check-in Time" prop="time">
          <el-col :span="11">
          <el-date-picker
            :disabled-date="disabledDate"
            value-format='YYYY-MM-DD'
            format='YYYY-MM-DD'
            v-model="hotelForm.date"
            type="date"
            placeholder="Pick a date"
            style="width: 100%;"
            clearable
          />
          </el-col>
          <el-col :span="2" class="text-center">
            <span class="text-gray-500">-</span>
          </el-col>
          <el-col :span="11">
          <el-time-picker
            value-format='HH:mm'
            format='HH:mm'
            v-model="hotelForm.time"
            placeholder="Pick a time"
            style="width: 100%"
          />
          </el-col>
        </el-form-item>

        <el-form-item label="Price" prop="price">
          <el-input v-model="hotelForm.price" />
        </el-form-item>

        <el-form-item label="Room Type" prop="type">
           <el-select v-model="hotelForm.type" placeholder="please select room type">
             <el-option label="Standard Room" value="Standard Room" />
             <el-option label="King Bed Room" value="King Bed Room" />
             <el-option label="Family Room" value="Family Room" />
             <el-option label="Presidential Suite" value="Presidential Suite" />
           </el-select>
         </el-form-item>

        <el-button type="primary" @click="AddHotel('hotelForm')">Create</el-button>
        <el-button type="" @click="cancel()">Cancel</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>
 
<script>
export default {
  name: 'HotelList',
  data() {
    const nameValidator = (rule, value, callback) => {
      const reg = /^[a-zA-Z]+$/;
      if (value === '') {
        callback(new Error('Please input the hotel name'));
      } else if (!reg.test(value)) {
        callback(new Error('The hotel name can only be English letters'));
      } else {
        callback();
      }
    };
    const priceValidator = (rule, value, callback) => {
      const reg = /^[1-9]\d*$/;
      if (value === '') {
        callback(new Error('Please input the price'));
      } else if (!reg.test(value)) {
        callback(new Error('Please input a valid number'));
      } else {
        callback();
      }
    };
    return {
      tableData: [
        {
          name: 'Sheraton',
          city: 'SHEN ZHEN',
          district: 'FU TIAN',
          date: '2024-09-20',
          time: '14:00',
          price: '953',
          type: 'Standard Room',
        },
        {
          name: 'Intercontinental',
          city: 'SHEN ZHEN',
          district: 'YAN TIAN',
          date: '2021-09-02',
          time: '13:00',
          price: '3940',
          type: 'King Bed Room',
        },
      ],
      hotelForm: {
        name: '',
        city: '',
        district: '',
        date: '',
        time: '',
        price: '',
        type: '',
      },
      districts: {
        'GUANG ZHOU': ['YUE XIU', 'LI WAN', 'HAI ZHU', 'TIAN HE', 'BAI YUN', 'HUANG PU', 'FAN YU', 'HUA DU', 'NAN SHA', 'CONG HUA', 'ZENG CHENG'],
        'SHEN ZHEN': ['FU TIAN', 'NAN SHAN', 'LUO HU', 'LONG GANG', 'PING SHAN', 'LONG HUA', 'GUANG MING', 'YAN TIAN'],
      },
      rules: {
        name: [
          { validator: nameValidator, trigger: ['blur', 'change'] },
          { required: true, trigger: true }
        ],
        city: [
          { required: true, message: 'Please input the city', trigger: ['blur', 'change'] },
        ],
        district: [
          { required: true, message: 'Please input the district', trigger: ['blur', 'change'] },
        ],
        time: [
          { required: true, message: 'Please input the time', trigger: ['blur', 'change'] },
        ],
        price: [
          { validator: priceValidator, trigger: ['blur', 'change'] },
          { required: true, trigger: true }
        ],
        type: [
          { required: true, message: 'Please input the room type', trigger: ['blur', 'change'] },
        ],
      },
      dialogVisible: false,
    };
  },
  watch: {
    'hotelForm.city'() {
      this.hotelForm.district = '';
    }
  },
  computed: {
    filteredDistricts() {
      return this.districts[this.hotelForm.city] || [];
    }
  },
  methods: {
    handleDelete(index) {
      this.tableData.splice(index, 1);
    },
    AddHotel(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const duplicateRow = this.tableData.find(item => 
            item.name === this.hotelForm.name && 
            item.city === this.hotelForm.city &&
            item.district === this.hotelForm.district &&
            item.type === this.hotelForm.type
          );

          if (duplicateRow) {
            this.$message.error('The same Hotel Name, City, District and Room Type already exists.');
            return false;
          }

          // 校验是否有相同的 Hotel Name, City, District 但不同 Room Type，且价格相同
          const sameHotelDiffRoomType = this.tableData.find(item =>
            item.name === this.hotelForm.name &&
            item.city === this.hotelForm.city &&
            item.district === this.hotelForm.district &&
            item.type !== this.hotelForm.type &&  // Room Type 必须不同
            item.price === this.hotelForm.price   // 价格不能相同
          );

          if (sameHotelDiffRoomType) {
            this.$message.error('Price must be different for the same Hotel Name, City and District but different Room Types.');
            return false;
          }

          this.tableData.push({ ...this.hotelForm });
          this.dialogVisible = false;
          this.$message.success('Hotel added successfully!');
        } else {
          console.log('error submit!!');
        }
      });
    },
    disabledDate(date) {
      const today = new Date();
      return date < today.setHours(0, 0, 0, 0);
    },
    resetForm() {
      this.$refs['hotelForm'].resetFields()
    },
    cancel() {
      this.$refs['hotelForm'].resetFields()
      this.dialogVisible = false
    },
  },
};
</script>

<style>
.el-button:hover {
    transform: scale(1.05);
}
.el-dialog__title{
    font-size: 20px;
    font-weight: bold;
}
</style>
