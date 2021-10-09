<template>
  <base-card>
    <h2>FInd your coach</h2>
    <span class="filter-option">
      <input type="checkbox" id="frontend" @change="changeFilter" checked />
      <label for="frontend">Frontend</label>
    </span>
    <span class="filter-option">
      <input type="checkbox" id="backend" @change="changeFilter" checked />
      <label for="backend">backend</label>
    </span>
    <span class="filter-option">
      <input type="checkbox" id="career" @change="changeFilter" checked />
      <label for="career">career</label>
    </span>
  </base-card>
</template>

<script>
export default {
  emits: ['filter-changed'],
  data() {
    return {
      filters: {
        frontend: true,
        backend: true,
        career: true
      }
    };
  },
  methods: {
    changeFilter(event) {
      const element_id = event.target.id;
      const isActive = event.target.checked;
      const updatedFilter = {
        ...this.filters,
        [element_id]: isActive
      };
      this.filters = updatedFilter;
      this.$emit('filter-changed', this.filters);
    }
  }
};
</script>

<style scoped>
h2 {
  margin: 0.5rem 0;
}

.filter-option {
  margin-right: 1rem;
}

.filter-option label,
.filter-option input {
  vertical-align: middle;
}

.filter-option label {
  margin-left: 0.25rem;
}

.filter-option.active label {
  font-weight: bold;
}
</style>
