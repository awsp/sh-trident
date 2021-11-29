<script>
  export let date = null;

  $: dateObject = date === null ? new Date() : new Date(date);
  $: dateObjectDuplicated = date === null ? new Date() : new Date(date);
  $: nextMonthObject = new Date(dateObjectDuplicated.setMonth(dateObjectDuplicated.getMonth() + 1));

  function foo() {
    const month = dateObject.getMonth() + 1;
    return {
      month: dateObject.getMonth() + 1,
      nextMonthObject,
    }
  }
</script>

<style lang="scss">

</style>

<!-- HTML -->
<div class="calendar">
  <!--{#each foo().month as month}-->
    month: {dateObject} <br>
    next month: {nextMonthObject}
  <!--{/each}-->
</div>