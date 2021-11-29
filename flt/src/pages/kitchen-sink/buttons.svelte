<script>
  import Button from '../../components/kitchen-sink/Button/Button.svelte';

</script>

<style lang="scss">
  div {
    margin: 0 0 21px;
  }
</style>

<!-- HTML -->
<div>
  <Button rounded>Rounded</Button>
</div>
<div>
  <Button rounded almostFlat>Rounded Almost Flat</Button>
</div>
<div>
  <Button almostFlat>Rounded Almost Flat</Button>
</div>
<div>
  <Button small>Rounded</Button>
</div>
<div>
  <Button large>Large</Button>
</div>
<div>
  <Button large rounded>Large</Button>
</div>
<div>
  <Button disabled>Disabled</Button>
</div>
<div>
  <Button theme="alert">Alert</Button>
</div>
<div>
  <Button theme="primary">Primary</Button>
</div>
<div>
  <Button theme="lavandula">Lavandula</Button>
</div>

<p>
  <Button full>Full</Button>
</p>