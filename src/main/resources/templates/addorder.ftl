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
        <input type="text" class="form-control" name="comment" placeholder="Комментарий"/>
        </div>
            <br>
            <div class="form-group row mt-3">
            <select id="executor" class="form-control" name="type"  placeholder="type">
                <option value="ukep" selected>ukep</option>
                <option value="cloud">cloud</option>
                <option value="sterra">sterra</option>

            </select>
            </div>
        </div>

        <div class="custom-file">
        <input type="file" name="file" id="customFile"/>
            <label class="custom-file-label" for="customFile">Выберите файл</label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="form-group">
        <button type="submit" class="btn btn-primary mt-2">Зарегистрировать</button>
        </div>
    </form>


</div>

</@c.page>