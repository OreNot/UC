<#import "parts/common.ftl" as c>

<@c.page>

<div>
    <form method="get" action="redact">
        <div class="form-group row mt-3">
            <div class="col-sm-9">
                <input type="text" class="form-control" name="fio" value="${statement.clientFio}"/>
            </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-sm-9">
                <input type="text" class="form-control" name="organization" value="${organization}"/>
            </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-sm-9">
                <input type="hidden" class="form-control" name="executor" value="${executor}"/>
            </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-sm-9">
                <!--<input type="text" class="form-control" name="comment" value="${statement.comment}"/>
                -->

                        <label for="exampleFormControlTextarea1">Комментарий</label>
                        <textarea class="form-control" name="comment" rows="3">${statement.comment}</textarea>

                <input type="hidden" class="form-control" name="id" value="${statement.id}"/>
                <input type="hidden" class="form-control" name="regDate" value="${statement.regDate}"/>
                <input type="hidden" class="form-control" name="authorName" value="${statement.authorName}"/>
                <input type="hidden" class="form-control" name="filename" value="${statement.filename}"/>
            </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-sm-9">
                <input type="text" class="form-control" name="type" disabled value="${statement.type}"/>
            </div>
        </div>

        <!--
                <div class="custom-file">
                <input type="file" name="file" id="customFile"/>
                    <label class="custom-file-label" for="customFile">Выберите файл</label>
                </div>

        <div class="file-upload-wrapper">
            <input type="file"  name="file" id="input-file-now" class="file-upload" />
            <label class="input-file-label" for="input-file-now">Заявление</label>
        </div>
-->
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group">
            <button type="submit" class="btn btn-primary mt-2">Назначить</button>
        </div>
    </form>


</div>
</@c.page>