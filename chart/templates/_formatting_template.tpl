{{- define "flashbot.fullname" -}}
{{ default "flashbot" .Values.nameOverride }}
{{- end -}}
