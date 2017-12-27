 function validarCampos(xhr, status, args, dlg, tb) {
        if(args.validationFailed) {
        	/*PF(Nome da caixa de dialogo).jp.effect(Efeito, tempo, força)*/
            PF(dlg).jq.effect("shake", {times:5}, 100);
        }
        else {
            PF(dlg).hide();
            PF(tb).clierFilters();
        }
    }