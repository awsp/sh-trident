<script>
  import {metatags} from '@roxi/routify'
  import Program from '../../components/manager/Program.svelte';
  import ProgramTable from '../../components/manager/ProgramTable.svelte';
  import DataContainer from '../../components/manager/DataContainer.svelte';
  import SearchResult from '../../components/manager/SearchResult.svelte';
  import {defaultProgramDatabank, formData} from '../../config/constant';

  metatags.title = 'Program Manager';

  const delay = 250;
  let promise;
  let timer;
  let programSelected = null;
  let newForm = false;
  let data = Object.assign({}, formData);
  let programDatabank = Object.assign({}, defaultProgramDatabank);

  $: profileSelected = programSelected !== null;

  async function queryProgram(searchQuery) {
    const response = await fetch(`http://192.168.0.199:8018/api/v1/query/program?title=${searchQuery}&sort=startDate,desc`);
    const json = await response.json();

    if (response.ok) {
      return json;
    }
    return new Error(json);
  }


  const addData = (e) => {
    e.preventDefault();
    programDatabank = [...programDatabank, data];
    resetData();
    newForm = false;
  };

  const resetData = () => {
    data = Object.assign({}, formData);
  };

  const addNew = () => {
    newForm = true;
  }

  const debounce = (value, which) => {
    if (which === 13 || (which >= 65 && which <= 90)) {
      clearTimeout(timer);
      timer = setTimeout(() => {
        if (value !== '') {
          promise = queryProgram(value);
        }
      }, delay);
    }
  }

  const cancel = () => {
    newForm = false;
  };

  function onSelectProfile(profile) {
    programSelected = profile;
  }

</script>

<style lang="scss">
  //$radius: 10px;
  //
  //div.table {
  //  border-radius: $radius;
  //  border: 1px solid #bfbfbf;
  //  box-shadow: 0 2px 5px 0 #dfdfdf;
  //  overflow: hidden;
  //  background: white;
  //  padding: 0 18px;
  //
  //  div.body {
  //    margin: 0 -18px;
  //  }
  //
  //  header {
  //    padding: 10px 0;
  //
  //    h2 {
  //      font-family: 'Open Sans', sans-serif;
  //      font-weight: 300;
  //      min-height: 32px;
  //      padding: 0;
  //      margin: 0;
  //      color: rgb(51, 65, 85);
  //      text-transform: capitalize;
  //    }
  //  }
  //
  //  footer {
  //    padding: 10px 18px;
  //    min-height: 20px;
  //  }
  //
  //  table {
  //    width: 100%;
  //    border-collapse: collapse;
  //    border-bottom-left-radius: $radius;
  //    border-bottom-right-radius: $radius;
  //
  //    thead {
  //      border-top: 1px solid #eee;
  //      border-bottom: 1px solid #eee;
  //      box-shadow: 0 2px 0 0 #eee;
  //
  //      th {
  //        padding: 10px 12px;
  //        text-align: left;
  //        color: rgb(100, 116, 139);
  //        font-size: 13px;
  //
  //        &:first-child {
  //          padding-left: 18px;
  //        }
  //
  //        &:last-child {
  //          padding-right: 18px;
  //        }
  //      }
  //    }
  //
  //    tbody {
  //      tr:nth-child(2n) td {
  //        background: #f5f7fd;
  //      }
  //
  //      td {
  //        font-size: 13px;
  //        padding: 5px 12px;
  //
  //        &:first-child {
  //          padding-left: 18px;
  //        }
  //
  //        &:last-child {
  //          padding-right: 18px;
  //        }
  //      }
  //    }
  //  }
  //}
  //
  //.program-manager {
  //  padding: 6px 10px;
  //}
</style>

<!-- HTML -->

<div class="program-manager">
  <input type="text" placeholder="Search"
         on:keyup={({target: {value}, which}) => debounce(value, which)}
         disabled={profileSelected}/>

  {#if !profileSelected}
    {#await promise}
      Searching
    {:then programs}
      {#if programs}
        <SearchResult {programs} on:select-profile={onSelectProfile}/>
      {/if}
    {:catch error}
      Error
    {/await}
  {:else}
    <div class="p-2">
      <Program data={programSelected.detail}>
        <ProgramTable slot="program-table"/>
        <DataContainer slot="data-accordion"/>
      </Program>
    </div>
  {/if}
</div>