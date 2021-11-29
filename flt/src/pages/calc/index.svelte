<script>
  let temperatureC = 20;
  let temperatureF = 80;
  $: toC = (temperatureF - 32) * 5 / 9;
  $: toF = (temperatureC * 9 / 5) + 32;
</script>

<input type="number" bind:value={temperatureC}/> => {toF.toFixed(2)}

<input type="number" bind:value={temperatureF}/> => {toC.toFixed(2)}