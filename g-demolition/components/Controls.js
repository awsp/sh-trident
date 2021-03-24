import styles from "../styles/Controls.module.scss";
import {
  Button,
  ButtonGroup,
  Classes,
  ControlGroup,
  Dialog,
  FormGroup,
  Icon,
  InputGroup,
  NumericInput,
  Position,
  Toaster
} from "@blueprintjs/core";
import React, {useReducer, useState} from "react";
import {useRouter} from "next/router";
import {KEYS} from "../constants";
import {createFeedFocus, deleteFocus} from "../helpers/feed-helper";

const Controls = ({children}) => {
  return <>
    <ButtonGroup fill={true} large={true} minimal={true} className={styles.focusControls}>
      {children}
    </ButtonGroup>
  </>;
};

Controls.AddFocus = ({callback}) => {
  const dialogReducer = (state, action) => {
    switch (action.type) {
      case 'open':
        return true;
      case 'close':
        return false;
    }
  };

  const formDefaults = {name: '', phrases: [], remarks: '', counter: 0, total: 0, subscription: {id: 0}};
  const [form, setForm] = useState(formDefaults);
  const [formDialog, dispatchFormDialog] = useReducer(dialogReducer, false);
  const [newPhrase, setNewPhrase] = useState('');
  const [isIMEMode, setIsIMEMode] = useState(false);
  const router = useRouter();
  const {subId} = router.query;

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

      createFeedFocus(form).then((data) => {
        const toaster = Toaster.create({
          position: Position.TOP,
        });

        if (data.id) {
          setForm(formDefaults);
          callback(true);
          dispatchFormDialog({type: 'close'})
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
    }, 500);
  };

  return (<>
    <Button icon="plus" intent="primary" onClick={() => dispatchFormDialog({type: 'open'})}/>
    <Dialog isOpen={formDialog} title="New Focus" className={styles.dialog}
            onClose={() => dispatchFormDialog({type: 'close'})}>
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
            <Button onClick={() => dispatchFormDialog({type: 'close'})}>Cancel</Button>
          </div>
        </div>
      </form>
    </Dialog>
  </>);
}

Controls.RemoveFocus = ({focusId, callback}) => {
  const confirmDialogReducer = (state, action) => {
    switch (action.type) {
      case 'open':
        return true;
      case 'close':
        return false;
    }
  };
  const [confirmDialog, dispatchConfirmDialog] = useReducer(confirmDialogReducer, false);

  const confirmation = () => {
    dispatchConfirmDialog({type: 'open'});
  };

  const confirmDelete = () => {
    if (focusId) {
      deleteFocus(focusId).then(() => {
        callback(true);
        dispatchConfirmDialog({type: 'close'});
      });
    }
  };

  return <>
    <Button icon="minus" intent="primary" onClick={confirmation} disabled={!focusId}/>
    <Dialog isOpen={confirmDialog}>
      <div className={styles.dialogForm}>
        <p>Confirm Delete? {focusId} aaa </p>
      </div>
      <div className={Classes.DIALOG_FOOTER}>
        <div className={styles.itemGroup}>
          <Button intent="primary" onClick={confirmDelete}> Delete </Button>
          <Button onClick={() => dispatchConfirmDialog({type: 'close'})}> Cancel </Button>
        </div>
      </div>
    </Dialog>
  </>;
};

Controls.History = () => {
  return (
    <Button icon="history" intent="primary"/>
  );
};

Controls.Settings = () => {
  return (
    <Button icon="settings" intent="primary"/>
  );
};

export default Controls;