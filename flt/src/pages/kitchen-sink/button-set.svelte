<script>

  import Button from '../../components/kitchen-sink/Button/Button.svelte';
  import ButtonSet from '../../components/kitchen-sink/ButtonSet/ButtonSet.svelte';
</script>

<style lang="scss">
  div {
    margin: 0 0 21px;
  }
</style>

<!-- HTML -->
<div>
  <ButtonSet>
    <Button>Play</Button>
    <Button>Stop</Button>
  </ButtonSet>
</div>

<div>
  <ButtonSet>
    <Button>Fast Backward</Button>
    <Button>Play</Button>
    <Button>Pause</Button>
    <Button>Stop</Button>
    <Button>Fast Forward</Button>
  </ButtonSet>
</div>

<div>
  <ButtonSet>
    <Button small>Fast Backward</Button>
    <Button small>Play</Button>
    <Button small>Pause</Button>
    <Button small>Stop</Button>
    <Button small>Fast Forward</Button>
  </ButtonSet>
</div>