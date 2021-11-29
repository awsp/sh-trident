<script>
  import Fa from 'svelte-fa';
  import DataEntry from './DataEntry.svelte';
  import {faPlus} from '@fortawesome/free-solid-svg-icons';
  import {opedData} from '../../config/constant';

  // Local constants
  const COMPONENT = 'DataContainer';

  // Local variables
  let debug = true;
  let controls = false;
  let editing = null;
  $: isEditing = editing !== null;

  let mutateOpEdData = Array.of(opedData);

  // Local functions
  function add() {
    editing = {
      id: mutateOpEdData.length + 1,
      oped: '',
      division: 1,
      title: '',
      artist: '',
      favScore: 0
    };
    mutateOpEdData = [...mutateOpEdData, editing];
  }

  function submit() {
    editing = null;
  }

  let dataId = null;

  function onEdit({detail}) {
    dataId = detail.dataEntryId;
  }
</script>

<style lang="scss">
</style>

<!-- HTML -->
<div class:debug>
  <header class="metadata">
    {COMPONENT}
  </header>

  {#each mutateOpEdData as data}
    <DataEntry {data} editing={data.id === dataId} on:edit={onEdit}/>
  {/each}

  <div class="text-center my-4">
    <button type="button"
            class="rounded-full p-3 border border-solid bg-white bg-opacity-50 shadow-xs"
            on:click={add}>
      <Fa icon={faPlus}/>
    </button>
  </div>
</div>