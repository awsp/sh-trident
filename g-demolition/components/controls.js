import styles from "../styles/Controls.module.scss";
import {
  Button,
  Classes,
  ControlGroup,
  Dialog,
  FormGroup,
  Icon,
  InputGroup,
  Menu,
  MenuItem,
  NumericInput,
  Popover,
  Position, Toaster
} from "@blueprintjs/core";
import React, {useState} from "react";
import {useRouter} from "next/router";
import {KEYS} from "../constants";

export function Controls() {
  const formDefaults = {name: '', phrases: [], remarks: '', counter: 0, total: 0, subscription: {id: 0}};
  const [form, setForm] = useState(formDefaults);
  const [formDialog, setFormDialog] = useState(false);
  const [newPhrase, setNewPhrase] = useState('');
  const [isIMEMode, setIsIMEMode] = useState(false);
  const router = useRouter();
  const {subId} = router.query;

  const closeDialog = () => {
    setFormDialog(false);
  };

  const openDialog = () => {
    setFormDialog(true);
  };

  const handleChange = (e) => {
    e.preventDefault();
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleCounterChange = (value) => {
    setForm({
      ...form,
      counter: value
    });
  };

  const handleTotalChange = (value) => {
    setForm({
      ...form,
      total: value
    });
  };

  const handlePhraseChange = (e) => {
    setNewPhrase(e.target.value);
  };

  const addSearchPhrase = (e) => {
    e.preventDefault();
    if (newPhrase !== '' && ((e.which === KEYS.ENTER && !isIMEMode) || e.button === 0)) {
      const phrases = form.phrases;
      if (phrases.indexOf(newPhrase) < 0) {
        phrases.push(newPhrase);
        setForm({
          ...form,
          phrases,
        })
        setNewPhrase('');
      }
    }
  };

  const removePhrase = (index) => {
    const phrases = form.phrases;
    phrases.splice(index, 1);
    setForm({
      ...form,
      phrases,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (subId) {
      let formCopy = Object.assign({}, form);
      formCopy.subscription.id = subId;
      setForm(formCopy);

      console.log(JSON.stringify(form));
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/focus`, {
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(form)
      });

      response.json().then((data) => {
        const toaster = Toaster.create({
          position: Position.TOP,
        });

        if (data.id) {
          setForm(formDefaults);
          closeDialog();
          toaster.show({
            message: `Focus ${data.name} is created`,
            intent: "success"
          });
        } else {
          toaster.show({
            message: `Failed to create focus. See console for errors. `,
            intent: "danger"
          });
          console.error(data);
        }
      });
    }
  };

  const compositionStart = (e) => {
    setIsIMEMode(true);
  };

  const compositionEnd = (e) => {
    // Allow 1 second for IME before re-enabling Enter to submit.
    setTimeout(() => {
      setIsIMEMode(false);
    }, 1000);
  };

  const menu = <Menu>
    <MenuItem text="New Focus" onClick={openDialog}/>
  </Menu>;

  return (<section className={styles.controls}>
    <Popover content={menu} position={Position.BOTTOM}>
      <Button icon="plus" text=" Actions " className="bp3-small"/>
    </Popover>

    <Dialog isOpen={formDialog} title="New Focus" className={styles.dialog}>
      <form>
        <div className={styles.dialogForm}>
          <FormGroup label="Name">
            <InputGroup placeholder="Give focus a name. " name="name" onChange={handleChange}
                        autoComplete="off" fill value={form.name}/>
          </FormGroup>

          <section className={styles.searchPhraseContainer}>
            <p>Search Phrases</p>
            <div className={styles.phraseList}>
              {
                form.phrases.length > 0 ? form.phrases.map((phrase, index) => (
                  <ControlGroup key={`ph-${index}`} className={styles.phraseItem}>
                    <InputGroup placeholder="Enter keywords" value={phrase} disabled={true}
                                className={styles.inputPhrase}/>
                    <Button onClick={removePhrase} icon="cross"/>
                  </ControlGroup>
                )) : <div className={styles.emptyList}>Please enter at least 1 phrase. </div>
              }

            </div>

            <ControlGroup key="new-phrase">
              <InputGroup leftElement={<Icon icon="tag"/>} placeholder="New Search Phrase" fill value={newPhrase}
                          onChange={handlePhraseChange} onKeyUp={addSearchPhrase} name="newPhrase"
                          onCompositionStart={compositionStart} onCompositionEnd={compositionEnd}
                          key="new-search-phrase"
                          autoComplete="off"/>
              <Button icon="plus" onClick={addSearchPhrase} disabled={newPhrase === ''}/>
            </ControlGroup>
          </section>

          <section className={styles.metadata}>
            <FormGroup label="Counter" inline={true}>
              <NumericInput name="counter" value={form.counter} min="0" onValueChange={handleCounterChange}/>
            </FormGroup>

            <FormGroup label="Total" inline={true}>
              <NumericInput name="total" value={form.total} min="0" onValueChange={handleTotalChange}/>
            </FormGroup>
          </section>

          <FormGroup label="Remarks">
            <textarea value={form.remarks} className={styles.remarks} name="remarks" onChange={handleChange}/>
          </FormGroup>
        </div>
        <div className={Classes.DIALOG_FOOTER}>
          <div className={styles.itemGroup}>
            <Button intent="primary" disabled={form.name === '' || form.phrases.length === 0}
                    onClick={handleSubmit}>Save Focus</Button>
            <Button onClick={closeDialog}>Cancel</Button>
          </div>
        </div>
      </form>
    </Dialog>
  </section>);
}