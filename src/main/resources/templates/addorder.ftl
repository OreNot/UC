<#import "parts/common.ftl" as c>
<@c.page>
<div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group row mt-3">
            <div class="col-sm-9">
        <input type="text" class="form-control" name="fio" placeholder="ФИО"/>
            </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-sm-9">
        <input type="text" class="form-control" name="organization" placeholder="Организация"/>
        </div>
        </div>
        <div class="form-group row mt-3">
            <div class="col-sm-9">
                <input type="text" class="form-control" name="inn" placeholder="ИНН"/>
            </div>
        </div>
        <!--<div class="form-group row mt-3">
            <div class="col-sm-9">
        <input type="text" class="form-control" name="comment" placeholder="Комментарий"/>
            </div>
        </div>
        -->
        <div class="form-group row mt-3">
            <div class="col-sm-9">
            <label for="exampleFormControlTextarea1">Комментарий</label>
            <textarea class="form-control" name="comment" rows="3"></textarea>
            </div>
        </div>
            <div class="form-group row mt-3">
                <div class="col-sm-9">
            <select id="executor" class="form-control" name="type"  placeholder="Тип">
                <option value="Квалиф." selected>Квалиф</option>
                <option value="Облачный">Облачный</option>
                <option value="sterra">sterra</option>

            </select>
                </div>

            </div>

<!--
        <div class="custom-file">
        <input type="file" name="file" id="customFile"/>
            <label class="custom-file-label" for="customFile">Выберите файл</label>
        </div>
        -->
        <div class="file-upload-wrapper">
            <input type="file"  name="file" id="input-file-now" class="file-upload" />
            <label class="input-file-label" for="input-file-now">Заявление</label>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group">
        <button type="submit" class="btn btn-primary mt-2">Зарегистрировать</button>
        </div>
    </form>


</div>

</@c.page>